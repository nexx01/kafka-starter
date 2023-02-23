package com.example.kafkastarter.producer;

import kafka.tools.ConsoleConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

//Порядок запуска:
//
//    Запускаем сначала Kafka если она не запущена,
//    Запускаем JavaKafkaProducerExamples, если у Вас в топике test.topic нет сообщений
//    Запускаем наш JavaKafkaConsumerExample
public class JavaKafkaConsumerExample {

    public static void main(String[] args) {
        String server = "localhost:9092";
        String topicName = "test.topic";
        String groupName = "test.group";

        var properties = new Properties();

        properties.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupName);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        final Consumer<Long, String> consumer = new KafkaConsumer<>(properties);

        var tp = new TopicPartition(topicName, 0);
        var tps = Arrays.asList(tp);
        consumer.assign(tps);
        consumer.seekToBeginning(tps);



        while (true){
            var consumerRecords = consumer.poll(30000);
            if (!consumerRecords.isEmpty()) {
                System.out.println("SUCCESS");
                System.out.println(consumerRecords.iterator().next().value());

            }

        }
        //consumer.close();



    }
}
