package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
public class Candle {

    @Id @GeneratedValue
    private Long id;

    private Instant timestamp;

    private String symbol;

    private BigDecimal open, high, low, close, volume;
}
