package com.nova.customer_registration.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    // Lee la dirección del broker Kafka desde application.yml (ej. localhost:29092)
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Usuario para autenticación SASL — definido en application.yml
    @Value("${kafka.sasl.username}")
    private String saslUsername;

    // Contraseña para autenticación SASL — definido en application.yml
    @Value("${kafka.sasl.password}")
    private String saslPassword;

    // Crea la fábrica de productores Kafka con toda la configuración necesaria
    @Bean
    public DefaultKafkaProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();

        // Dirección del broker al que el productor se conectará para enviar mensajes
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // El key de cada mensaje Kafka se serializa como texto plano (String)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // El value de cada mensaje se serializa como JSON automáticamente
        // JsonSerializer convierte cualquier objeto Java a su representación JSON
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Indica que la conexión usa autenticación pero SIN cifrado TLS
        // SASL_PLAINTEXT = autenticación activada, transporte sin SSL
        // En producción con datos sensibles usar SASL_SSL en su lugar
        props.put("security.protocol", "SASL_PLAINTEXT");

        // Mecanismo de autenticación: PLAIN envía usuario y contraseña en texto plano
        // Es aceptable solo sobre redes privadas o junto con SSL
        props.put("sasl.mechanism", "PLAIN");

        // Cadena JAAS (Java Authentication and Authorization Service) que le dice a Kafka
        // qué módulo de login usar y con qué credenciales.
        // PlainLoginModule es el estándar para SASL/PLAIN en Kafka.
        props.put("sasl.jaas.config",
                PlainLoginModule.class.getName() + " required " +
                        "username=\"" + saslUsername + "\" " +
                        "password=\"" + saslPassword + "\";");

        // Activa idempotencia — Kafka asigna un Producer ID único a este productor
        // y numera cada mensaje por partición. Si el broker ya recibió un mensaje
        // con el mismo ID + número de secuencia, lo descarta sin duplicarlo.
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        // Con idempotencia activa, acks DEBE ser "all": el broker espera confirmación
        // de todos los réplicas antes de dar el mensaje por escrito.
        // Sin esto, Kafka lanza ConfigException al arrancar.
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        // Reintentos infinitos — con idempotencia es seguro porque el broker deduplica.
        // Sin idempotencia, reintentos infinitos SÍ causarían mensajes duplicados.
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);

        // Kafka impone un límite de 5 requests en vuelo por conexión cuando
        // idempotencia está activa. Poner más de 5 lanza ConfigException al arrancar.
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);

        // Tiempo máximo total para entregar un mensaje incluyendo todos los reintentos.
        // Si pasa este tiempo sin éxito, el productor reporta fallo al llamador.
        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 120_000);

        // Construye y retorna la fábrica con todas las propiedades configuradas
        return new DefaultKafkaProducerFactory<>(props);
    }

    // KafkaTemplate es la clase de Spring que usas para enviar mensajes desde cualquier Service
    // Internamente usa el producerFactory para crear productores cuando los necesita
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
