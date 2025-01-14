package com.example.ms.teachers.management.kafka.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Teacher {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("subject")
    private Subject subject = null;

    @JsonProperty("salary")
    private Salary salary = null;
}
