package com.pipeline.configuration;

import com.pipeline.engine.core.configuration.Definition;
import com.pipeline.engine.core.configuration.PipelineDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class PipelineConfiguration {

    @Bean
    @ConfigurationProperties("pipeline")
    public PipelineDefinition definition(){
        return new PipelineDefinition();
    }

}


