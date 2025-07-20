package com.example.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Service;

import com.example.model.Candle;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Instant;

public class CoinbaseClient {
    private final WebClient wc = WebClient.builder()
            .baseUrl("https://api.exchange.coinbase.com")
            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
            .build();

    public List<Candle> fetch(String productId, int granularity) {
        List<List<BigDecimal>> raw = wc.get()
            .uri(uri -> uri.path("/products/{productId}/candles")
                .queryParam("granularity", granularity)
                .build(productId))
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<List<BigDecimal>>>() {})
            .block();

        return raw.stream()
            .map(candle -> {
                Candle c = new Candle();
                c.setTimestamp(Instant.ofEpochSecond(candle.get(0).longValue()));
                c.setOpen(candle.get(3));
                c.setHigh(candle.get(2));
                c.setLow(candle.get(1));
                c.setClose(candle.get(4));
                c.setVolume(candle.get(5));
                return c;
            })
            .collect(Collectors.toList());
    }
    
}
