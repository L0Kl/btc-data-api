package com.example.btc_data_api.web;

import com.example.btc_data_api.model.Candle;
import com.example.btc_data_api.repo.CandleRepository;
import com.example.btc_data_api.service.CandleService;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/candles")
public class CandleController {
    private final CandleRepository candleRepository;
    private final CandleService candleService;

    public CandleController(CandleRepository candleRepository, CandleService candleService) {
        this.candleRepository = candleRepository;
        this.candleService = candleService;
    }

    @GetMapping("/count")
    public long countCandles() {
        return candleRepository.count();
    }

    @GetMapping("/fetch")
    public void fetchCandles() {
        candleService.fetchAndStoreCandles();
    }

    @GetMapping
    public List<Candle> getCandles(@RequestParam(required = false) Instant start, @RequestParam(required = false) Instant end) {
        if (start == null || end == null) {
            return candleRepository.findByTimestampBetween(Instant.now().minusSeconds(3600), Instant.now());
        } else if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time must be before end time");
        } else if (start.isAfter(Instant.now()) || end.isAfter(Instant.now())) {
            throw new IllegalArgumentException("Start and end times must not be in the future");
        } else if (start.equals(end)) {
            throw new IllegalArgumentException("Start and end times must not be the same");
        } else {
            return candleRepository.findByTimestampBetween(start, end);
        }
    }
}
