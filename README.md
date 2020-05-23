# Java Programming for Apache Kafka

You must have kafka installed to run the project.

To run the first kafka producer, open any compiler and run the file Producer.java

To get the message to the consumer console,
type the following command from the another terminal.
```aidl
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-third-application
```
Now, you will recieve the message in the terminal where you have run the above command,
```
hello world
```
To run the producer with callbacks:


A callback method the user can implement to provide asynchronous handling of request completion. This method will be called when the record sent to the server has been acknowledged. Exactly one of the arguments will be non-null.

Run the ProducerWithCallback class and to see the messages run the 'kafka-console-consumer' command. You will get the messgaes in the following way.
```aidl
hello world0
hello world1
hello world2
hello world3
hello world4
hello world5
hello world6
hello world7
hello world8
hello world9
```