package com.github.simpleProject.kafka.simpleProject;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Logger logger = LoggerFactory.getLogger(ProducerWithCallback.class);
        String bootstrapServers = "127.0.0.1:9092";
        // create producer properties

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // create the producer <key,value>
        KafkaProducer<String, String> producer = new KafkaProducer(properties);
        for (int i=0; i<10; i++) {

            // create producer record
            String topic = "first_topic";
            String value = "hello world" + Integer.toString(i);
            String key = "id_"+Integer.toString(i);
            ProducerRecord<String, String> record =
                    new ProducerRecord(topic,key,value);
            logger.info("key:"+key);
            // id_0 is going to partition 1
            // id_1 partition 0


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
            }).get(); // block the .send() to make it synchronous but don't do it in production
        }
        //flush data
        producer.flush();
        //close data
        producer.close();
    }

}
