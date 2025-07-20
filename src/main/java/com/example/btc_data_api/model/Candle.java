package com.example.btc_data_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(
  name = "candle",
  uniqueConstraints = @UniqueConstraint(
    name = "uk_candle_unique",
    columnNames = {"timestamp", "symbol", "granularity"}
  )
)
public class Candle {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String granularity;

    private BigDecimal open, high, low, close, volume;
}
