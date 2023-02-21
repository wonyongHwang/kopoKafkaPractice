
package com.kopo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
import java.util.Properties;

class ProducerCallback implements Callback {

    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            System.out.printf("Partition: %d, Offset: %d", metadata.partition(), metadata.offset());
        } else {
            exception.printStackTrace();
        }
    }
}

public class MyProducerASyncCallback {

    public static void main(String[] args){
    
         Properties props = new Properties();
    	 props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
    	 props.put("acks", "all");
    	 props.put("retries", 0);
    	 props.put("linger.ms", 1);
    	 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    	 KafkaProducer<String, String> producer = new KafkaProducer<>(props);
         for (int i = 0; i < 100; i++){
                producer.send(new ProducerRecord<String, String>("javatopic", Integer.toString(i), Integer.toString(i)), new ProducerCallback());
		System.out.println(i);
         }
         producer.close();
         
   }
}
