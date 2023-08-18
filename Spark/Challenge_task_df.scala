package org.itc
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{SaveMode, SparkSession, types}
import org.apache.spark.sql.catalyst.plans._
import sparksql.spark.implicits._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window


object Challenge_task_df extends App {
  System.setProperty("hadoop.home.dir", "C:\\winutils")
  Logger.getLogger("org").setLevel(Level.ERROR)

  val sparkconf = new SparkConf()
  sparkconf.set("spark.app.name", "Spark Challenge")
  sparkconf.set("spark.master", "local[*]")
  val spark = SparkSession.builder().config(sparkconf).getOrCreate()
  //

  val dfmenu = spark.read
    .format("jdbc")
    .option("driver", "com.mysql.cj.jdbc.Driver")
    .option("url", "jdbc:mysql://localhost:3306/assignment")
    .option("dbtable", "menu")
    .option("user", "root")
    .option("password", "123456")
    .load()
  val dfmembers = spark.read
    .format("jdbc")
    .option("driver", "com.mysql.cj.jdbc.Driver")
    .option("url", "jdbc:mysql://localhost:3306/assignment")
    .option("dbtable", "members")
    .option("user", "root")
    .option("password", "123456")
    .load()
  val dfsales = spark.read
    .format("jdbc")
    .option("driver", "com.mysql.cj.jdbc.Driver")
    .option("url", "jdbc:mysql://localhost:3306/assignment")
    .option("dbtable", "sales")
    .option("user", "root")
    .option("password", "123456")
    .load()

  println("What is the total amount each customer spent at the restaurant?")
  dfsales.join(dfmenu, dfmenu("product_id") === dfsales("product_id")).
    groupBy("customer_id").agg(sum("price").alias("Price Sum")).sort(desc(columnName = "Price Sum")).show()

  println("How many days has each customer visited the restaurant?")
  dfsales.groupBy(col1 = "customer_id").agg(countDistinct(columnName = "order_date").alias(alias = "Number of days")).
    sort(sortCol = "customer_id").show()

  println("What was the first item from the menu purchased by each customer?")
  var specWindow = Window.partitionBy("customer_id").orderBy("order_date")

  dfsales.withColumn("rank_cust", dense_rank().over(specWindow)).
    join(dfmenu, dfmenu("product_id") === dfsales("product_id")).
    where(col("rank_cust") === 1).distinct().select("customer_id","product_name").show

  println("What is the most purchased item on the menu and how many times was it purchased by all customers?")

  dfsales.join(dfmenu,dfmenu("product_id")=== dfsales("product_id")).
    groupBy(col1 = "product_name").agg(count(dfsales("product_id")).alias("No of Times")).show()

  println("Which item was the most popular for each customer?")

  dfmenu.join(dfsales, dfmenu("product_id") === dfsales("product_id")).
    groupBy(dfsales("customer_id"),dfmenu("product_name")).agg(count(dfmenu("product_id")).alias("No of Times")).
    orderBy(dfsales("customer_id")).show()

  var membersView = dfmembers.createTempView("members")
  var salesView = dfsales.createTempView("sales")
  var menuView = dfmenu.createTempView("menu")

  println("Which item was purchased first by the customer after they became a member?")

  spark.sql("select * from (select members.customer_id, members.join_date, sales.order_date, menu.product_name," +
    "row_number() over (partition by members.customer_id order by sales.order_date asc) as r_count " +
    "from members join sales on members.customer_id = sales.customer_id join menu on menu.product_id = sales.product_id " +
    "where members.join_date < sales.order_date) as a where r_count = 1").
    select("customer_id","product_name","order_date")show()

  println("Which item was purchased just before the customer became a member?")

  spark.sql("WITH beforemember AS (SELECT mem.customer_id,s.product_id, ROW_NUMBER() OVER( PARTITION BY mem.customer_id ORDER BY s.order_date) AS row_num " +
    "FROM members mem JOIN sales s ON mem.customer_id = s.customer_id AND s.order_date < mem.join_date) " +
    "SELECT customer_id,product_name FROM beforemember JOIN menu m ON beforemember.product_id = m.product_id " +
    "WHERE row_num = 1 ORDER BY customer_id ASC").show()

  println("What is the total items and amount spent for each member before they became a member?")

  spark.sql("SELECT s.customer_id,COUNT(s.product_id) AS total_items, SUM(me.price) AS total_price FROM sales s JOIN members m  ON (s.customer_id = m.customer_id AND s.order_date < m.join_date) " +
    "JOIN menu me  ON (s.product_id = me.product_id) GROUP BY s.customer_id ORDER BY s.customer_id").show()

  println("If each $1 spent equates to 10 points and sushi has a 2x points multiplier - how many points would each customer have?")

  spark.sql("SELECT s.customer_id,sum(point_tab.points) as total_points FROM sales s JOIN " +
    "(SELECT product_id,CASE WHEN product_id=1 THEN price*20 ELSE price * 10 END AS points FROM menu) point_tab on (point_tab.product_id=s.product_id) GROUP BY 1").show()

  println("In the first week after a customer joins the program (including their join date) they earn 2x points on all items, not just sushi - how many points do customer A and B have at the end of January?")

  var df10= spark.sql("select sales.customer_id, count(sales.product_id) as Total_Item, sum(menu.price * 20) as Total_Points " +
    "from members inner join sales  on members.customer_id = sales.customer_id " +
    "inner join menu  on sales.product_id = menu.product_id " +
    "where sales.order_date between members.join_date and DATE_ADD(members.join_date, 7)  group by 1").show()



}
