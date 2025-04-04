package com.example.ms.teachers.management.core.port.out;

import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;

public interface InstructorManagerPortOut {

    TeacherListDtoOutput getTeachers(String authorization, String origin);
    TeacherDtoOutput getTeacherById(String authorization, String origin, Integer id);
    TeacherDtoOutput createTeacherResponse(String authorization,
                                           String origin,
                                           Instructor instructor);
    TeacherDtoOutput updateTeacherResponse(String authorization,
                                           String origin,
                                           Instructor instructor, Integer id);
    TeacherDtoOutput deleteTeacherResponse(String authorization, String origin, Integer id);
}
