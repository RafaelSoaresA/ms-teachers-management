package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InstructorList   {
  @JsonProperty("instructors")
  private List<Instructor> instructors = null;
}
