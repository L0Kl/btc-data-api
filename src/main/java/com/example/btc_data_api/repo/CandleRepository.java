package com.example.btc_data_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.btc_data_api.model.Candle;

import java.time.Instant;
import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {
    List<Candle> findByTimestampBetween(Instant start, Instant end);
}
