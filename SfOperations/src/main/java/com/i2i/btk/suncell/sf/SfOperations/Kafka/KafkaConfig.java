package com.i2i.btk.suncell.sf.SfOperations.Kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Bean
    public ConsumerFactory<String, KafkaModel> KafkaModelConsumer(){
    	Map<String, Object> map = new HashMap<>();
    	map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"ec2-18-184-134-198.eu-central-1.compute.amazonaws.com:9092");
    	map.put(ConsumerConfig.GROUP_ID_CONFIG,"4");
    	map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        return new DefaultKafkaConsumerFactory<String, KafkaModel>(map, new StringDeserializer(),new JsonDeserializer<>(KafkaModel.class));
    	
    }
    
    @Bean 
    public ConcurrentKafkaListenerContainerFactory<String, KafkaModel> KafkaModelListener(){
    	ConcurrentKafkaListenerContainerFactory<String, KafkaModel> factory = new ConcurrentKafkaListenerContainerFactory<String, KafkaModel>();
    	factory.setConsumerFactory(KafkaModelConsumer());
    	return factory;
    	
    }


}
