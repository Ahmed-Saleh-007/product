package com.ejada.product.service.controller;

import com.ejada.product.service.service.SSEService;
import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SSEController {

    private final SSEService sseService;

    @GetMapping(value = "/flowable/time", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<String> streamTimeFlowable() {
        return sseService.streamTimeFlowable();
    }

    @GetMapping(value = "/flux/time", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamTimeFlux() {
        return sseService.streamTimeFlux();
    }

}
