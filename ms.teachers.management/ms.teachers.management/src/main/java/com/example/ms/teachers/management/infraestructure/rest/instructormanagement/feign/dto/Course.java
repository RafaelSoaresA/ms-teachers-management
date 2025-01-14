package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Course   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;
}
