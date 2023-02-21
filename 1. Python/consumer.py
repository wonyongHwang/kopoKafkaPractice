from kafka import KafkaConsumer

consumer = KafkaConsumer('kopo-topic',bootstrap_servers='master:9092,slave1:9092,slave2:9092',enable_auto_commit=True,auto_offset_reset='latest')

for message in consumer:
        print("Topic : %s, Partition: %d, Offset: %d, Key: %s, Value: %s" % (message.topic, message.partition, message.offset, message.key, message.value.decode('utf-8')))


