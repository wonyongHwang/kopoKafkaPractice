from kafka import KafkaProducer
from json import dumps

producer = KafkaProducer(bootstrap_servers='master:9092,slave1:9092,slave2:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))
future = producer.send('kopo-topic', 'Hello World!!!')
result = future.get(timeout=60)
print(result)
print("END")
