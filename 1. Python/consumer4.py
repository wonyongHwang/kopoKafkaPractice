from kafka import KafkaConsumer
from kafka import TopicPartition

consumer = KafkaConsumer(bootstrap_servers='master:9092,slave1:9092,slave2:9092',enable_auto_commit=True,auto_offset_reset='earliest')
consumer.assign([TopicPartition('kopo-topic', 1)])
#msg = next(consumer)
#print(msg)
for message in consumer:
        print("Topic : %s, Partition: %d, Offset: %d, Key: %s, Value: %s" % (message.topic, message.partition, message.offset, message.key, message.value.decode('utf-8')))


