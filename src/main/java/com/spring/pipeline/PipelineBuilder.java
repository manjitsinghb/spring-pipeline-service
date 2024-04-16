package com.spring.pipeline;

import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class PipelineBuilder {

    public static <U,V, C extends Consumer<U>, P extends Publisher<V>, T extends Transformer<U,V>> Disposable buildPipeline(C consumer, T transformer, P publisher) {
      Flux<U> flux = Flux.generate(consumer::consumeMessage,(state, sink) -> {
            sink.next(state);
            return state;
        });
        ConnectableFlux<U> connectableFlux = flux.publish();
        ConnectableFlux<V> transformedFlux = connectableFlux.map((u) -> transformer.transform(u)).publish();
        transformedFlux.subscribe((e) -> publisher.publish(e));
        return transformedFlux.connect();
    }
}