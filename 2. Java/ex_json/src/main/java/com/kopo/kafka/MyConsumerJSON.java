package com.kopo.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.Arrays;
import java.util.Properties;
import java.io.*;
public class MyConsumerJSON {

    public static void main(String[] args) {
        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        Properties configs = new Properties();
        // 환경 변수 설정
        configs.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");     // kafka server host 및 port
        configs.put("session.timeout.ms", "10000");             // session 설정
        configs.put("group.id", "telegroup");                // group 설정
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");    // key deserializer
        configs.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");  // value deserializer
        // configs.put("value.deserializer",deserializer);
configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kopo.kafka");
        configs.put("enable.auto.commit","true");
        configs.put("auto.commit.interval.ms","1000");
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(configs);    // consumer 생성
        consumer.subscribe(Arrays.asList("kopo-topic"));      // topic 설정

        BufferedWriter bw = null;
        while (true) {  // 계속 loop를 돌면서 producer의 message를 띄운다.
            ConsumerRecords<String, Object> records = consumer.poll(500);
            for (ConsumerRecord<String, Object> record : records) {
                String s = record.topic();
                if ("kopo-topic".equals(s)) {
                    String tmp  = "offset = " + record.offset() + " key =" + record.key() + " value =" + record.value();
                    System.out.println(tmp);
					User user = new User();
					user = (User)record.value();
					System.out.println("User name is "+user.getName()+" and its id is "+user.getUserId());
                   
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }
    }

}

/*
class User
{
    private String userId;
    private String name;

        public String getUserId() {
                return userId;
        }
        public void setUserId(String userId) {
                this.userId = userId;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String toString() {
                return "User [userId=" + userId + " name="+ name + "]";
        }
}
*/
