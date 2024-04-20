package com.pipeline.transformer;

import com.amazonaws.services.sqs.model.Message;
import com.spring.api.Transformer;

import java.util.List;

public class StringTransformer implements Transformer<List<Message>,String> {
    @Override
    public String transform(List<Message> s) {
        return "transformed "+s;
    }
}
