package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Instructor {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("course")
  private Course course;

  @JsonProperty("wage")
  private Wage wage;

}
