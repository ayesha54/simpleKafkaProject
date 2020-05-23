# Java Programming for Apache Kafka

You must have kafka installed to run the project.

To run the first kafka producer, open any compiler and run the file Producer.java

To get the message to the consumer console,
type the following command from the another terminal.
```aidl
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-third-application
```
Now, you will recieve the message,
```
hello world
```
