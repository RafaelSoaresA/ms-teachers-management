package com.example.ms.teachers.management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectDtoOutput {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

}
