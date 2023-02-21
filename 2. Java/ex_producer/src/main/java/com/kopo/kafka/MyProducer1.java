package com.kopo.kafka;

import java.util.*;
import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Producer;
/**
 * Hello world!
 *
 */
public class MyProducer1 
{
    public static void main( String[] args )
    {
    Properties producerProps = new Properties();
    producerProps.put("bootstrap.servers","master:9092,slave1:9092,slave2:9092");
    producerProps.put("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    producerProps.put("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    producerProps.put("acks","1");
    producerProps.put("retries",1);
    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProps);
    for (int i=0;i<2000;i++){
      ProducerRecord data = new ProducerRecord<String,String>("kopo-topic","Java " + i); 
      Future<RecordMetadata> recordMetadata = producer.send(data);
    }
    producer.close();
    }
}
