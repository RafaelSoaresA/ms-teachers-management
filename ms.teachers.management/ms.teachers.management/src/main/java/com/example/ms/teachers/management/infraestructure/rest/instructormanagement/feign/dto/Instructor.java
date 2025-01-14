package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto;

import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Instructor {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("fullName")
  private String fullName = null;

  @JsonProperty("course")
  private Course course = null;

  @JsonProperty("wage")
  private Wage wage = null;
}
