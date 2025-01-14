package com.example.ms.teachers.management.core.port.out.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TeacherListDtoOutput {
  private List<TeacherDtoOutput> teachers;

}
