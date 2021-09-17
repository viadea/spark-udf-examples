package info.openkb.spark.udf;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions.*;
import org.apache.spark.sql.types.DataTypes;

import org.joda.time.format.DateTimeFormat;

public class MySubStringUDF {
	public static void main(String[] args) {
		try {
			SparkConf sparkConf = new SparkConf().setAppName("my-substring-udf");
			SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

			Dataset<Row> people = spark.read().format("csv").option("header", "true").load("src/main/resources/people.csv");
			people.createOrReplaceTempView("people");
			people.show(false);

			// Create an UDF to create a substring
			UDF1<String, String> mySubString = new UDF1<String, String>() {
				@Override
				public String call(String s) throws Exception {
					return s.substring(0,1) + s.substring(2,3);
				}
			};

			spark.udf().register("mySubString", mySubString, DataTypes.StringType);

			spark.sql("SELECT id,firstname,lastname,mySubString(lastname) AS lastname_substr FROM people").show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
