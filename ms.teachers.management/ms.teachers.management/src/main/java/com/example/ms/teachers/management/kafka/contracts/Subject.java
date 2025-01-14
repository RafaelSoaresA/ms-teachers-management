package com.example.ms.teachers.management.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Subject {
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;
}
