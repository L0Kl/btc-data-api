package com.example.btc_data_api.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.btc_data_api.repo.CandleRepository;

@Service
public class CandleService {
    private final CandleRepository candleRepository;
    private final CoinbaseClient coinbaseClient;

    public CandleService(CandleRepository candleRepository, CoinbaseClient coinbaseClient) {
        this.candleRepository = candleRepository;
        this.coinbaseClient = coinbaseClient;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000) // Fetch candles every minute
    public void fetchAndStoreCandles() {
        var list = coinbaseClient.fetch("BTC-USD", 60);
        candleRepository.saveAll(list);
    }
}
