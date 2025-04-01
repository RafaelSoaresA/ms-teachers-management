package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper;

import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.InstructorList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InstructorListToTeacherListOutputMapper {

   public TeacherListDtoOutput responseGetTeacherListMapper(InstructorList instructorList) {
       TeacherListDtoOutput teacherListDtoOutput = new TeacherListDtoOutput();

       if(CollectionUtils.isNotEmpty(instructorList.getInstructors())){
           teacherListDtoOutput.setTeachers(instructorList.getInstructors().stream()
                   .filter(Objects::nonNull)
                   .map(instructor -> {
                       InstructorToTeacherOutputMapper mapper = new InstructorToTeacherOutputMapper();
                       return mapper.responseGetTeacher(instructor);
                   }).toList());
       }
       return teacherListDtoOutput;
   }
}
