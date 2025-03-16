package com.ejada.product.service.service;

import io.reactivex.rxjava3.core.Flowable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@RequiredArgsConstructor
public class SSEService {

    private static final long MAX_PERIOD_SECONDS = 60L;
    private static final long INTERVAL_SECONDS = 5L;

    public Flowable<String> streamTimeFlowable() {
        AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
        return Flowable.interval(INTERVAL_SECONDS, TimeUnit.SECONDS)
                .takeUntil(tick -> (System.currentTimeMillis() - startTime.get()) / 1000 >= MAX_PERIOD_SECONDS)
                .map(tick -> LocalDateTime.now().toString());
    }

    public Flux<ServerSentEvent<String>> streamTimeFlux() {
        AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
        return Flux.interval(Duration.ofSeconds(INTERVAL_SECONDS))
                .takeUntil(tick -> (System.currentTimeMillis() - startTime.get()) / 1000 >= MAX_PERIOD_SECONDS)
                .map(tick -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(tick))
                        .event("time-event")
                        .data(LocalDateTime.now().toString())
                        .build());
    }

}
