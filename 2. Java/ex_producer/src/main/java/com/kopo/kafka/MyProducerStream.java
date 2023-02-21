package com.kopo.kafka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;


public class MyProducerStream {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
         Properties props = new Properties();
         props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
         props.put("acks", "all");
         props.put("retries", 0);
         props.put("linger.ms", 1);
         props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
         props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

         KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String s;
        Process p;
        try {
            String[] cmd = {"/bin/sh", "-c", "curl -s -H 'Accept: application/json'  https://stream.wikimedia.org/v2/stream/recentchange | jq"};
            p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null){
                System.out.println(s);
                producer.send(new ProducerRecord<String, String>("kopo-topic", s));
            }
            producer.close();
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        }
    }
}
