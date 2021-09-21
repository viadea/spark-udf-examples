package info.openkb.spark.scalaudf

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf
import org.apache.spark.SparkConf

object MySubStringScalaUDF{
  val mySubString=udf[String, String]((s: String) => {
    s.substring(0,1)+s.substring(2,3)
  })

}