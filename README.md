# spark-udf-examples
This is a repo for demo simple spark UDF examples.

## Compile
```bash
cd java-udf
mvn package
```
The jar file is generated: `spark-udf-examples/java-udf/target/sparkjavaudf-0.0.1.jar`.

## Spark-submit
```bash
cd java-udf

spark-submit \
  --class info.openkb.spark.udf.FormatDateUDF \
  --master spark://MASTER_IP:7077 \
  /path/to/sparkjavaudf-0.0.1.jar
```



