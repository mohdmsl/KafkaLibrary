package com.msl.kafkalibrary

import com.bdbizviz.dp.kafka.config.KafkaConfig
import org.apache.kafka.clients.admin.{AdminClient, NewPartitions}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

import java.io.{File, PrintWriter}
import java.time.Duration
import java.util
import java.util.Properties
import scala.collection.JavaConversions.iterableAsScalaIterable
import scala.collection.JavaConverters.asJavaCollectionConverter
import scala.util.control.Breaks
import scala.util.control.Breaks.break

object test {

  def main(args: Array[String]): Unit = {
    val config = KafkaConfig.getConfig
    val eventConsumer = new KafkaConsumer[String, String](config)
    val writer = createLocal
    val topic = "api_event_" + "comp1622552829525_inst_2534"
    val adminClient = AdminClient.create(config)
    /*val map = new util.HashMap[String, NewPartitions]()
    val np = NewPartitions.increaseTo(5)
    map.put(topic, np)
    adminClient.createPartitions(map)*/
   val xx = eventConsumer.partitionsFor(topic)

    val topicPartition = (0 until 3).toList.
      map(x => new TopicPartition(topic, x))
    eventConsumer.assign(topicPartition.asJavaCollection)
    eventConsumer.seekToBeginning(topicPartition.asJavaCollection)
    var count = 0
    var unchangedCount = 0
    var prevCount = 0
    Breaks.breakable {
      while (true) {
        val records = eventConsumer.poll(Duration.ofMillis(1000))
        if (!records.isEmpty) {
          val map = records.map { x =>
            prevCount = count
            count = count + 1
            val data = x.value()
            write(writer, data)
          }
          //map.foreach(println(_))
        }
        else {
          prevCount = count
        }


        if (count == prevCount) {
          unchangedCount = unchangedCount + 1
        }
        else {
          unchangedCount = 0
        }
        if (unchangedCount > 30) {
          writer.close()
          break
        }
      }
    }
    println("DONE!!")
    println("total records count: " + count)

    // println(count)
  }

  def createLocal = {
    val dir = "/home/bizviz-admin/Documents/temp/"
    val writer = new PrintWriter(new File(dir + "sample.json"))
    writer
  }

  def write(writer: PrintWriter, data: String) = {
    writer.write(data + "\n")
  }

}

