package com.example.ms.teachers.management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalaryDtoOutput {
  @JsonProperty("amount")
  private Float amount = null;

  @JsonProperty("currency")
  private String currency = null;

}
