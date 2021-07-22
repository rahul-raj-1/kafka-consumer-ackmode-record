# Kafka Consumer with Error Handling ACK MODE RECORD.

Start up the Zookeeper.
zookeeper-server-start.bat ..\..\config\zookeeper.properties

Start up the Kafka Broker.
kafka-server-start.bat ..\..\config\server.properties

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic mytopic-3 --from-beginning  --isolation-level=read_committed

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic mytopic-3 --from-beginning  --isolation-level=read_uncommitted


kafka-topics.bat --create --topic mytopic-5 -zookeeper localhost:2181 --replication-factor 1 --partitions 4


kafka-topics.bat --describe --zookeeper localhost:2181 --topic mytopic-4


kafka-console-producer --bootstrap-server localhost:9092 --topic mytopic-3


{"bar":"Fifth"}

kafka-consumer-groups --bootstrap-server localhost:9092  --group mytopic-1-groupid --topic mytopic-3 --reset-offsets --to-offset 15 --execute


