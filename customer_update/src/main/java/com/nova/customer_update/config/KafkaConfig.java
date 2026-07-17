package com.nova.customer_update.config;

import com.nova.customer_update.dto.event.CreateCustomerEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.sasl.username}")
    private String saslUsername;

    @Value("${kafka.sasl.password}")
    private String saslPassword;

    @Value("${kafka.consumer.group-id}")
    private String groupId;


    @Bean
    public ConsumerFactory<String, CreateCustomerEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                PlainLoginModule.class.getName() + " required " +
                        "username=\"" + saslUsername + "\" " +
                        "password=\"" + saslPassword + "\";");

        JsonDeserializer<CreateCustomerEvent> deserializer = new JsonDeserializer<>(CreateCustomerEvent.class);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateCustomerEvent> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CreateCustomerEvent>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setMissingTopicsFatal(false);
        return factory;
    }
}
