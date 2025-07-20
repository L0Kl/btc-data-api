package com.example.web;

import com.example.model.Candle;
import com.example.repo.CandleRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/candles")
public class CandleController {
    private final CandleRepository candleRepository;
    public CandleController(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    @GetMapping
    public List<Candle> getCandles(@RequestParam Instant start, @RequestParam Instant end) {
        return candleRepository.findByTimestampBetween(start, end);
    }
}
