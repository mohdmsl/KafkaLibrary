name := "kafka_library"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.7"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.7"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.7"
libraryDependencies += "com.springml" %% "spark-sftp" % "1.1.5"



//supporting project dependencies for UAT
libraryDependencies += "com.bdbizviz.dp" % "kafka_2.11" % s"${sys.env.getOrElse("BASE_COMPONENT_VERSION","6.0.0-1")}"
libraryDependencies += "com.bdbizviz.dp" % "dp_entities_2.11" % s"${sys.env.getOrElse("BASE_COMPONENT_VERSION","6.0.0-1")}"
libraryDependencies += "com.bdbizviz.dp" % "logger_2.11" % s"${sys.env.getOrElse("BASE_COMPONENT_VERSION","6.0.0-1")}"
// https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "4.9.1"


// supporting project dependencies for DEV & QA
/*libraryDependencies += "com.bdbizviz.dp" %  "dp_entities_2.11" % "1.2.52-SNAPSHOT"
libraryDependencies += "com.bdbizviz.dp" % "kafka_2.11" % "1.0.0-SNAPSHOT"
libraryDependencies+="com.bdbizviz.dp"%"logger_2.11"%"2.0.1-SNAPSHOT"*/

libraryDependencies += "org.scalaj" % "scalaj-http_2.11" % "2.3.0"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.9"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.9"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.9"
