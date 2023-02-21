from kafka import KafkaConsumer

consumer = KafkaConsumer('news-topic',group_id='kopoconsumer',bootstrap_servers='localhost:9092',enable_auto_commit=True,auto_offset_reset='latest',fetch_min_bytes=1, heartbeat_interval_ms=3000, session_timeout_ms=10000) 

print(".")
for message in consumer:
	print("..")
	print("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition, message.offset, message.key, message.value))



