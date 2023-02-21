from kafka import KafkaProducer
from json import dumps
import time

producer = KafkaProducer(acks=0, bootstrap_servers='master:9092,slave1:9092,slave2:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))

start = time.time()
for i in range(10000):
	data = 'message ' + str(i)
	future = producer.send('kopo-topic',data)
	result = future.get(timeout=10) #result.topic, result.partition, result.offset
print("elapsed : ", time.time() - start)
print('END')
