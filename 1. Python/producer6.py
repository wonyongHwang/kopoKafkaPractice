import requests
from sseclient import SSEClient as EventSource

url = "https://stream.wikimedia.org/v2/stream/recentchange"

headers = {
    "User-Agent": "kopo-kafka-practice/1.0 (test@example.com)"
}

session = requests.Session()
session.headers.update(headers)

for event in EventSource(url, session=session):
    print(event.data)
