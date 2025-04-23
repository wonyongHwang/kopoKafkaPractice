## 📖 [PreCondition]
#### Zookeeper, Kafka is running

## 📖 1. at source root directory :
mvn install

## 📖 2. Prepare a Kafka Topic :
```
  kafka-topics.sh --create --topic kopo-topic --bootstrap-server master:9092,slave1:9092,slave2:9092 –replication-factor 1 --partitions 1
```
## 📖 2-1. [Java Producer] execute jar as below:
```
  java -cp target/kafka-example-1.0.jar:/root/.m2/repository/org/apache/kafka/kafka_2.13/3.4.0/kafka_2.13-3.4.0.jar:/root/.m2/repository/org/apache/kafka/kafka-clients/3.4.0/kafka-clients-3.4.0.jar:/root/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/root/.m2/repository/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar:/root/.m2/repository/org/springframework/kafka/spring-kafka/2.3.0.RELEASE/spring-kafka-2.3.0.RELEASE.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.2/jackson-databind-2.12.2.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.2/jackson-core-2.12.2.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.2/jackson-annotations-2.12.2.jar:/root/.m2/repository/org/springframework/spring-core/5.3.0/spring-core-5.3.0.jar:/root/.m2/repository/org/springframework/spring-beans/5.3.0/spring-beans-5.3.0.jar:/root/.m2/repository/org/springframework/spring-messaging/5.3.0/spring-messaging-5.3.0.jar com.kopo.kafka.MyProducerJSON
```
## 📖 2-2. [Consumer] execute kafka-console-consumer.sh as below:
```
  kafka-console-consumer.sh --bootstrap-server master:9092,slave1:9092,slave2:9092 --property print.key=true --property key.separator=":"  --topic kopo-topic
```
## 📖 2-3. [Java Consumer] execute jar as below:
```
  java -cp target/kafka-example-1.0.jar:/root/.m2/repository/org/apache/kafka/kafka_2.13/3.4.0/kafka_2.13-3.4.0.jar:/root/.m2/repository/org/apache/kafka/kafka-clients/3.4.0/kafka-clients-3.4.0.jar:/root/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/root/.m2/repository/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar:/root/.m2/repository/org/springframework/kafka/spring-kafka/2.3.0.RELEASE/spring-kafka-2.3.0.RELEASE.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.2/jackson-databind-2.12.2.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.2/jackson-core-2.12.2.jar:/root/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.2/jackson-annotations-2.12.2.jar:/root/.m2/repository/org/springframework/spring-core/5.3.0/spring-core-5.3.0.jar:/root/.m2/repository/org/springframework/spring-beans/5.3.0/spring-beans-5.3.0.jar:/root/.m2/repository/org/springframework/spring-messaging/5.3.0/spring-messaging-5.3.0.jar  com.kopo.kafka.MyConsumerJSON
```
