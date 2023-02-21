from kafka import KafkaConsumer

consumer = KafkaConsumer('news-topic',group_id='kopo-consumer2',bootstrap_servers='master:9092,slave1:9092,slave2:9092',enable_auto_commit=True,auto_offset_reset='latest',fetch_min_bytes=1, heartbeat_interval_ms=3000, session_timeout_ms=10000) 

while True:
	message = consumer.poll(1.0)

	for tp, mg in message.items():
		for m in mg:
			print ("%s : %d %d : key=%s value=%s" % ( tp.topic, tp.partition, m.offset, m.key, m.value))


