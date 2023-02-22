package Kafka.javaKafka;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer {
public static void main(String[] args) throws IOException {
		Properties configs = new Properties();
		configs.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092"); 
		configs.put("acks", "all");
		configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   
		configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

		Scanner sc = new Scanner(System.in);
			while(true) {
				System.out.print("kafka 전달 메시지 입력 :");
				String i = sc.nextLine();
				producer.send(new ProducerRecord<String, String>("kopo-topic", i));
			
				if(i.equals("exit")){
					break;
				}
			}
		producer.flush();
		producer.close();
	}
}
