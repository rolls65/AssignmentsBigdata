package org.stock.demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object streamingDataFromkafka extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder()
    .master("local[2]")
    .appName("My Streaming Application")
    .config("spark.sql.shuffle.partitions", 3)
    .config("spark.streaming.stopGracefullyOnShutdown","true")
    .config("spark.sql.streaming.schemaInference","true").getOrCreate()

  val df = spark.read.format("kafka").option("kafka.bootstrap.servers","ip-172-31-13-101.eu-west-2.compute.internal:9092, ip-172-31-3-80.eu-west-2.compute.internal:9092, ip-172-31-5-217.eu-west-2.compute.internal:9092, ip-172-31-9-237.eu-west-2.compute.internal:9092").option("startingOffset","earliest").option("subscribe","stockdata").load()

  df.show()
}
