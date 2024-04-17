package com.pipeline.producer;

import com.spring.api.Publisher;

public class ConsolePublisher implements Publisher<String> {
    @Override
    public void publish(String s) {
        System.out.println(this.getClass().getName()+" "+s);
    }
}
