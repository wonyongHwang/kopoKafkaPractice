from kafka import KafkaConsumer

consumer = KafkaConsumer('kopo-topic',group_id='kopo-consumer',bootstrap_servers='master:9092,slave1:9092,slave2:9092',enable_auto_commit=True,auto_offset_reset='latest',fetch_min_bytes=1) 

while True:
	message = consumer.poll(1.0)
	#print (message.keys()) #print (message.values())
        #print "Offset: %d, Key: %s, Value: %s" % (message.offset, message.key, message.value.decode('utf-8'))

	for tp, mg in message.items():
		for m in mg:
			print ("%s : %d %d : key=%s value=%s" % ( tp.topic, tp.partition, m.offset, m.key, m.value))


