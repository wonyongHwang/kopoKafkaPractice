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
public class MyProducer2
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
    //producerProps.put("batch.size",20000);
    //producerProps.put("linger.ms",1); // 0 : no wait
    //producerProps.put("buffer.memory",24568545); //32M by default
    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(producerProps);

	while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input>> ");
            String message = sc.nextLine();

            ProducerRecord<String, String> record = new ProducerRecord<>("javatopic", message);
            try {
                producer.send(record);

            } catch (Exception e) {
                // exception
		System.out.println("exception occurs "+e);
            } finally {
                producer.flush();
            }

            if(message.equals("quit" )) {
                producer.close();
                break;
            }
        }
    }
}
