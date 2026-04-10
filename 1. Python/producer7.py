from kafka import KafkaProducer
from json import dumps
import json
import requests
from sseclient import SSEClient as EventSource

# Kafka Producer 생성
producer = KafkaProducer(
    acks=1,
    compression_type='gzip',
    bootstrap_servers='master:9092,slave1:9092,slave2:9092',
    value_serializer=lambda x: dumps(x).encode('utf-8')
)

url = 'https://stream.wikimedia.org/v2/stream/recentchange'

# ✅ 핵심: User-Agent 헤더 추가
headers = {
    "User-Agent": "kopo-kafka-practice/1.0 (test@example.com)"
}

# requests 세션 생성
session = requests.Session()
session.headers.update(headers)

# SSE 연결
for event in EventSource(url, session=session):
    if event.event == 'message':
        try:
            change = json.loads(event.data)
        except ValueError:
            pass
        else:
            message = '{user} edited {title}'.format(**change)
            print(message)
            future = producer.send('kopo-topic', value=message)
            result = future.get(timeout=60)
