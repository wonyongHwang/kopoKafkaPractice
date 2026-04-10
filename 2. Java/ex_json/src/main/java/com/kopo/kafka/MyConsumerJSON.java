package com.kopo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Arrays;
import java.util.Properties;
import java.time.Duration;

public class MyConsumerJSON {

    public static void main(String[] args) {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        configs.put("session.timeout.ms", "10000");
        configs.put("group.id", "telegroup");

        configs.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        configs.put("value.deserializer",
                "org.springframework.kafka.support.serializer.JsonDeserializer");

        // 🔥 핵심 설정 3개
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.kopo.kafka.User");
        configs.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);

        configs.put("enable.auto.commit", "true");
        configs.put("auto.commit.interval.ms", "1000");

        KafkaConsumer<String, User> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList("kopo-user-topic"));

        while (true) {

            ConsumerRecords<String, User> records =
                    consumer.poll(Duration.ofMillis(500));

            for (ConsumerRecord<String, User> record : records) {

                User user = record.value();

                System.out.println("offset = " + record.offset()
                        + " key = " + record.key());

                System.out.println("User name is "
                        + user.getName()
                        + " and its id is "
                        + user.getUserId());

                UserInfo userInfo = user.getUserInfo();
                if (userInfo != null) {
                    System.out.println(" - tel : " + userInfo.tel);
                    System.out.println(" - addr : " + userInfo.addr);
                }
            }
        }
    }
}
