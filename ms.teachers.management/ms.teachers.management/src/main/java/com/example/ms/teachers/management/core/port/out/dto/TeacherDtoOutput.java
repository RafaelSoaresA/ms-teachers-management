package com.example.ms.teachers.management.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeacherDtoOutput {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("subject")
  private SubjectDtoOutput subject = null;

  @JsonProperty("salary")
  private SalaryDtoOutput salary = null;

}
