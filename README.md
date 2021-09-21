# spark-udf-examples
This is a repo for demo simple spark UDF examples.

# 1. JAVA UDF
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

# 2. Scala UDF
## Compile

```bash
cd scala-udf
mvn package
```
The jar file is generated: `spark-udf-examples/scala-udf/target/sparkscalaudf-0.0.1.jar`.

## Spark-shell

###
```
import info.openkb.spark.scalaudf.MySubStringScalaUDF._
df.select(mySubString(col("somecol")).as("newcol") ).show(false)
```
