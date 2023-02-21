from kafka import KafkaProducer
from json import dumps
import time

producer = KafkaProducer(acks=1,compression_type='gzip', bootstrap_servers='master:9092,slave1:9092,slave2:9092', value_serializer=lambda x: dumps(x).encode('utf-8'), key_serializer=str.encode)

start = time.time()
for i in range(10000):
	data = 'message ' + str(i)
	if i%2 == 1:
		future = producer.send('kopo-topic',key="1",value=data)
	else :
		future = producer.send('kopo-topic',key="2",value=data)
producer.flush()	
print("elapsed : ", time.time() - start)
result = future.get(timeout=60)
print(result)
print('END')
