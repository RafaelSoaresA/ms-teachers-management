package com.example.ms.teachers.management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SalaryDto   {
  @JsonProperty("amount")
  private Float amount = null;

  @JsonProperty("currency")
  private String currency = null;

}
