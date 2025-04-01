package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign;

import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class FallbackInstructorManagement implements FallbackFactory<InstructorManagementFeign> {
    @Override
    public InstructorManagementFeign create(Throwable cause) {
        return new InstructorManagementFeign() {
            @Override
            public InstructorList getInstructor( @RequestHeader(value = "Authorization") String authorization,
                                                 @RequestHeader(value = "origin") String origin){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor getInstructorById(@RequestHeader(value = "Authorization") String authorization,
                                                @RequestHeader(value = "origin") String origin, @RequestParam Integer id){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor createInstructor(
                                               @RequestBody Instructor instructor){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor updateInstructor(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestHeader(value = "origin") String origin,
                                               @RequestBody Instructor instructor, @RequestParam Integer id){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }

            @Override
            public Instructor deleteInstructor(@RequestHeader(value = "Authorization") String authorization,
                                               @RequestHeader(value = "origin") String origin,
                                               @RequestParam Integer id){
                throw new RuntimeException("Internal Server Error - Erro inesperado no processamento da requisição. Por favor, tente novamente em alguns instantes");
            }
        };
    }
}
