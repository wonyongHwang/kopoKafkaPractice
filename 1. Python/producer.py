from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='master:9092,slave1:9092,slave2:9092')
future = producer.send('kopo-topic', 'Hello World!!!'.encode('utf-8'))
result = future.get(timeout=60)
print(result)
print("END")
