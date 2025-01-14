package com.example.ms.teachers.management.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TeacherDto   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("subject")
  private SubjectDto subject = null;

  @JsonProperty("salary")
  private SalaryDto salary = null;

}
