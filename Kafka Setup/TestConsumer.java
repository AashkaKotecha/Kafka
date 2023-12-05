

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TestConsumer {
    

    public static void main(String[] args) {

        String bootstrapServers = "192.168.24.104:7701,192.168.24.105:7701";
        String grp_id = "test_consumer";
        String topic = "TestABC";
        //Creating consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grp_id);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //Subscribing
        consumer.subscribe(Arrays.asList(topic));
        System.out.println("Before Loop starts");
        //polling
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            System.out.println("Loop starts");
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Key: " + record.key() + ", Value:" + record.value());
                System.out.println("Partition:" + record.partition() + ",Offset:" + record.offset());
            }


        }
    }
}
