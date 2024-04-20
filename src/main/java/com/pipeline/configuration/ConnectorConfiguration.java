package com.pipeline.configuration;

import com.amazonaws.services.sqs.model.Message;
import com.pipeline.consumer.MysqlConsumer;
import com.pipeline.producer.ConsolePublisher;
import com.pipeline.producer.KafkaPublisher;
import com.pipeline.transformer.StringTransformer;
import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConnectorConfiguration {

    @Bean("mysqlConsumer")
    public Consumer<String> consumer(){
        return new MysqlConsumer();
    }

    @Bean("transformer")
    public Transformer<List<Message>,String> transformer(){
        return new StringTransformer();
    }

    @Bean("kakfaPublisher")
    public Publisher<String> kafkaPublisher() {
        return new KafkaPublisher();
    }

    @Bean("consolePublisher")
    public Publisher<String> consolePublisher() {
        return new ConsolePublisher();
    }
}
