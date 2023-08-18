--1. What is the total amount each customer spent at the restaurant?

  SELECT customer_id,sum(price) as total_price  
  FROM sales s LEFT JOIN menu m on (s.product_id = m.product_id)
  GROUP BY customer_id
  ORDER BY customer_id
  
-- 2. How many days has each customer visited the restaurant?
  SELECT customer_id,count(distinct order_date) as total_days  
  FROM sales s 
  LEFT JOIN 
  menu m on (s.product_id = m.product_id)
  GROUP BY 1
  ORDER BY 1
  
-- 3. What was the first item from the menu purchased by each customer?

  WITH purchase_item AS (
  SELECT s.customer_id, s.order_date, m.product_name,
    DENSE_RANK() OVER(PARTITION BY s.customer_id 
      ORDER BY s.order_date) AS rank_cust
  FROM sales s
  JOIN menu m
    ON s.product_id = m.product_id
)
SELECT customer_id, product_name
FROM purchase_item
WHERE rank_cust = 1
GROUP BY customer_id, product_name;
  
-- 4. What is the most purchased item on the menu and how many times was it purchased by all customers?

SELECT m.product_name,COUNT(s.product_id) AS most_purchased_item
FROM sales s
JOIN menu m ON s.product_id = m.product_id
GROUP BY m.product_name

-- 5. Which item was the most popular for each customer?
WITH most_sale AS (
  SELECT s.customer_id, m.product_name, COUNT(m.product_id) AS order_count,
  DENSE_RANK() OVER( PARTITION BY s.customer_id 
      ORDER BY COUNT(s.customer_id) DESC) AS rank_cust
  FROM menu m
  JOIN sales s ON m.product_id = s.product_id
  GROUP BY s.customer_id, m.product_name
)
SELECT customer_id, 
  product_name, 
  order_count
FROM most_sale 
WHERE rank_cust = 1;

-- 6. Which item was purchased first by the customer after they became a member?

   WITH joinmember AS (
 SELECT mem.customer_id,s.product_id,
    ROW_NUMBER() OVER( PARTITION BY mem.customer_id
      ORDER BY s.order_date) AS row_num
 FROM members mem
 JOIN sales s ON mem.customer_id = s.customer_id
    AND s.order_date > mem.join_date
    )
SELECT customer_id,product_name 
FROM joinmember
JOIN menu m ON joinmember.product_id = m.product_id
WHERE row_num = 1
ORDER BY customer_id ASC;

-- 7. Which item was purchased just before the customer became a member?

WITH beforemember AS (
 SELECT mem.customer_id,s.product_id,
 ROW_NUMBER() OVER( PARTITION BY mem.customer_id
      ORDER BY s.order_date) AS row_num
 FROM members mem
 JOIN sales s ON mem.customer_id = s.customer_id
    AND s.order_date < mem.join_date
    )
SELECT customer_id,product_name 
FROM beforemember
JOIN menu m ON beforemember.product_id = m.product_id
WHERE row_num = 1
ORDER BY customer_id ASC;

-- 8. What is the total items and amount spent for each member before they became a member?
SELECT s.customer_id, 
  COUNT(s.product_id) AS total_items, SUM(me.price) AS total_price
FROM sales s
JOIN members m
  ON (s.customer_id = m.customer_id AND s.order_date < m.join_date)
JOIN menu me
  ON (s.product_id = me.product_id)
GROUP BY s.customer_id
ORDER BY s.customer_id;

-- 9.  If each $1 spent equates to 10 points and sushi has a 2x points multiplier - how many points would each customer have?

SELECT s.customer_id,sum(point_tab.points) as total_points 
FROM sales s
JOIN 
(SELECT product_id,CASE WHEN product_id=1 THEN price*20
ELSE price * 10 END AS points FROM menu) point_tab 
on (point_tab.product_id=s.product_id) 
GROUP BY 1

-- 10. In the first week after a customer joins the program (including their join date) they earn 2x points on all items, not just sushi - how many points do customer A and B have at the end of January?
 select sales.customer_id, count(sales.product_id) as Total_Item, 
 sum(menu.price * 20) as Total_Points from members
  inner join sales
  on members.customer_id = sales.customer_id
  inner join menu
  on sales.product_id = menu.product_id
  where sales.order_date between members.join_date and DATE_ADD(members.join_date, INTERVAL 7 DAY)
  group by 1