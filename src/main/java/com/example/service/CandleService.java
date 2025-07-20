package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import com.example.repo.CandleRepository;


public class CandleService {
    private final CandleRepository candleRepository;
    private final CoinbaseClient coinbaseClient;

    public CandleService(CandleRepository candleRepository, CoinbaseClient coinbaseClient) {
        this.candleRepository = candleRepository;
        this.coinbaseClient = coinbaseClient;
    }

    @Scheduled(fixedRate = 60000) // Fetch candles every minute
    public void fetchAndStoreCandles() {
        var list = coinbaseClient.fetch("BTC-USD", 60);
        candleRepository.saveAll(list);
    }
}
