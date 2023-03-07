package com.kopo.kafka;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;



public class MyProducerJSON {
    public static void main(String[] args) throws Exception {
         Properties props = new Properties();
         props.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
         props.put("acks", "1");
         props.put("retries", 0);
         props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
         props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");

         KafkaProducer<String, Object> producer = new KafkaProducer<>(props);

        String s;
        Process p;
        try {
	    for(int i=0;i<10;i++){
	        User user = new User();
                user.setUserId(Integer.toString(i));
                user.setName("wonyong");
                producer.send(new ProducerRecord<String, Object>("kopo-topic", "key "+i, user));
            }
            producer.close();
           // p.waitFor();
           // System.out.println("exit: " + p.exitValue());
            //p.destroy();
        } catch (Exception e) {
        }
    }
}

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
