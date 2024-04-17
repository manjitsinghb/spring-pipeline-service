package com.pipeline.consumer;

import com.spring.api.Consumer;

public class MysqlConsumer implements Consumer<String> {
    @Override
    public String consumeMessage() {
        return "messageConsumed";
    }
}
