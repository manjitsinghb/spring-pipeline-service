package com.pipeline.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.model.Message;
import com.pipeline.sink.SQSSink;
import com.pipeline.source.SQSSource;
import com.pipeline.source.adapter.SQSConfiguration;
import com.spring.api.Consumer;
import com.spring.api.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SQSSpringConfiguration {

    @Bean("sqsConsumerConfig")
    @ConfigurationProperties("pipeline.configuration.sqsconsumer")
    public SQSConfiguration sqsConfiguration(){
        return new SQSConfiguration();
    }

    @Bean("sqsPublisherConfig")
    @ConfigurationProperties("pipeline.configuration.sqspublisher")
    public SQSConfiguration sqsPublisher(){
        return new SQSConfiguration();
    }

    @Bean("sqsConsumer")
    public Consumer<List<Message>> sqsSource(@Qualifier("sqsConsumerConfig") SQSConfiguration sqsConfiguration){
        SQSSource sqsSource = new SQSSource(sqsConfiguration);
        return sqsSource::receiveMessage;
    }

    @Bean("sqsPublisher")
    public Publisher sqsSink(@Qualifier("sqsPublisherConfig")SQSConfiguration sqsConfiguration){
        SQSSink sqsSink = new SQSSink(sqsConfiguration);
        return sqsSink::publishMessage;
    }

}
