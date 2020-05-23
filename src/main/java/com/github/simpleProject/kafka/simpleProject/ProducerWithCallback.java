package com.github.simpleProject.kafka.simpleProject;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Properties;

public class ProducerWithCallback {
    public static void main(String[] args){
        final Logger logger = LoggerFactory.getLogger(ProducerWithCallback.class);
        String bootstrapServers = "127.0.0.1:9092";
        // create producer properties

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // create the producer <key,value>
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i=0; i<10; i++) {

            // create producer record
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "hello world" + Integer.toString(i));
            // send data - asynchronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        logger.info("Recieved new metadata. \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition:" + recordMetadata.partition() + "\n" +
                                "offset:" + recordMetadata.offset() + "\n" +
                                "Timestamp:" + recordMetadata.timestamp());

                    } else {
                        logger.error("Error while producing", e);
                    }
                }
            });
        }
        //flush data
        producer.flush();
        //close data
        producer.close();
    }
}
