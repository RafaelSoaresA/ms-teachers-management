package com.example.ms.teachers.management.kafka.contracts;

import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String uuid;
    private String createdDate;
    private List<Teacher> teachers;
}
