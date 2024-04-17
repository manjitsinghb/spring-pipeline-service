package com.pipeline.transformer;

import com.spring.api.Transformer;

public class StringTransformer implements Transformer<String,String> {
    @Override
    public String transform(String s) {
        return "transformed "+s;
    }
}
