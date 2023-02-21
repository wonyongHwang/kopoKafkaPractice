from kafka import KafkaProducer
from json import dumps
import time

producer = KafkaProducer(acks=0, bootstrap_servers='master:9092,slave1:9092,slave2:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))

def on_send_success(record_metadata):
    print(record_metadata.topic)
    print(record_metadata.partition)
    print(record_metadata.offset)

def on_send_error(excp):
    log.error('I am an errback', exc_info=excp)
    # handle exception

start = time.time()
for i in range(10000):
	data = 'my async message ' + str(i)
	producer.send('kopo-topic',data).add_callback(on_send_success).add_errback(on_send_error)
	print(i)
# block until all async messages are sent
producer.flush()
print("elapsed : ", time.time() - start)
print('END')
