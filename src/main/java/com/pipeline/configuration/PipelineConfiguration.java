package com.pipeline.configuration;

import com.pipeline.engine.core.configuration.PipelineDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfiguration {

    @Bean
    @ConfigurationProperties("pipeline")
    public PipelineDefinition definition(){
        return new PipelineDefinition();
    }
}


