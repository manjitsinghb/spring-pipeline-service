package com.pipeline.starter;

import com.pipeline.configuration.PipelineConfiguration;
import com.pipeline.engine.core.Pipeline;
import com.pipeline.engine.core.builder.PipelineBuilder;
import com.pipeline.engine.core.configuration.Definition;
import com.pipeline.engine.core.configuration.PipelineDefinition;
import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PipelineStarter {

    @Autowired
    private PipelineConfiguration pipelineConfiguration;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PipelineDefinition pipelineDefinition;

    @PostConstruct
    public void startPipelines(){
        for(Definition definition : pipelineDefinition.getDefinitions()){
            Consumer consumer = getConsumerByName(definition.getConsumer());
            Transformer transformer = getTransformerByName(definition.getTransformer());
            List<Publisher> publisherList = getPublisherList(definition.getPublishers());
            new PipelineBuilder(definition.getName()).withConsumer(consumer)
                    .withTransformer(transformer)
                    .withPublishers(publisherList).withParallelism(definition.getParallelism()).buildPipeline();
        }
    }

    private List<Publisher> getPublisherList(List<String> publishers) {
       return publishers.stream().map(e -> applicationContext.getBean(e)).filter(Objects::nonNull)
               .map(e -> (Publisher)e).collect(Collectors.toList());
    }

    private Consumer getConsumerByName(String consumerName) {
        Consumer consumer = (Consumer)applicationContext.getBean(consumerName);
        if(consumer == null){
            throw new IllegalArgumentException("Unable to find bean by name "+consumerName);
        }
        return consumer;
    }

    private Transformer getTransformerByName(String transformerName) {
        Transformer transformer = (Transformer) applicationContext.getBean(transformerName);
        if(transformer == null){
            throw new IllegalArgumentException("Unable to find bean by name "+transformer);
        }
        return transformer;
    }

}
