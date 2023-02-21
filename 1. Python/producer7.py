from kafka import KafkaProducer
from json import dumps
import json
from sseclient import SSEClient as EventSource

producer = KafkaProducer(acks=1,compression_type='gzip', bootstrap_servers='master:9092,slave1:9092,slave2:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))

url = 'https://stream.wikimedia.org/v2/stream/recentchange'
for event in EventSource(url):
    if event.event == 'message':
        try:
            change = json.loads(event.data)
        except ValueError:
            pass
        else:
            message = '{user} edited {title}'.format(**change)
            print(message)
            future = producer.send('kopo-topic',value=message)
            result = future.get(timeout=60)

print('END')
