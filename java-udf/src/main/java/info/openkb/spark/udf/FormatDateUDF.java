package info.openkb.spark.udf;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.functions.*;
import org.apache.spark.sql.types.DataTypes;

import org.joda.time.format.DateTimeFormat;

public class FormatDateUDF {
	public static void main(String[] args) {
		try {
			SparkConf sparkConf = new SparkConf().setAppName("format-date-udf");
			SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

			Dataset<Row> people = spark.read().format("csv").option("header", "true").load("src/main/resources/people.csv");
			people.createOrReplaceTempView("people");
			people.show(false);

			// Create an UDF to re-format the date string
			UDF1<String, String> formatDate = new UDF1<String, String>() {
				@Override
				public String call(String s) throws Exception {
					return DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(s).toString("yyyyMMdd");
				}
			};

			spark.udf().register("format_date", formatDate, DataTypes.StringType);

			spark.sql("SELECT id,firstname,lastname,birthday,format_date(birthday) AS new_birthday FROM people").show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
