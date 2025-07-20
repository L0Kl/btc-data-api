package com.example.repo;

import com.example.model.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, Long> {
    List<Candle> findByTimestampBetween(Instant start, Instant end);
}
