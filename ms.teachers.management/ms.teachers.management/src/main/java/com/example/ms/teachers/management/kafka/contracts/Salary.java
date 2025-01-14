package com.example.ms.teachers.management.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Salary {
    @JsonProperty("amount")
    private Double amount = null;

    @JsonProperty("currency")
    private String currency = null;

    @JsonProperty("payFrequency")
    private String payFrequency = null;
}
