package com.spring;

import com.spring.pipeline.PipelineBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class App {
    public static void main(String[] args) {
        PipelineBuilder.buildPipeline(() -> {return "hi";}, (e) -> e+"bye", System.out::println);
    }
}