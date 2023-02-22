package Kafka.javaKafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        configs.put("session.timeout.ms", "10000");             
        configs.put("group.id", "javagroup");                
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");    
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);    
        consumer.subscribe(Arrays.asList("kopo-topic"));      
        while (true) {  
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                String s = record.topic();
                if ("kopo-topic".equals(s)) {
                    System.out.println(record.value());
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }   
    }
}