package com.spring.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConsumerStarter {

    public ConsumerStarter(@Autowired ApplicationContext applicationContext){
        List<Consumer> consumerList = findConsumers(applicationContext);


    }

    private List<Consumer> findConsumers(ApplicationContext applicationContext) {
        return applicationContext.getBeansWithAnnotation(Consumer.class);
    }

}
