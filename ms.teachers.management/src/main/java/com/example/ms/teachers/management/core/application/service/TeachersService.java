package com.example.ms.teachers.management.core.application.service;

import com.example.ms.teachers.management.api.domain.entities.Salary;
import com.example.ms.teachers.management.api.domain.entities.Subject;
import com.example.ms.teachers.management.api.domain.entities.Teacher;
import com.example.ms.teachers.management.api.domain.repositories.SalaryRepository;
import com.example.ms.teachers.management.api.domain.repositories.SubjectRepository;
import com.example.ms.teachers.management.api.domain.repositories.TeacherRepository;
import com.example.ms.teachers.management.core.port.in.TeacherPortIn;
import com.example.ms.teachers.management.core.port.in.dto.TeacherDto;
import com.example.ms.teachers.management.core.port.in.dto.TeacherListDto;
import com.example.ms.teachers.management.core.port.out.InstructorManagerPortOut;
import com.example.ms.teachers.management.core.port.out.SendEventProducerPortOut;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeachersService implements TeacherPortIn {

    private final InstructorManagerPortOut instructorManagerPortOut;
    private final SendEventProducerPortOut sendEventProducerPortOut;
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;
    private final SalaryRepository salaryRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public TeacherListDto listTeacher(String authorization, String origin) {
        log.info("[SERVICE - teacherManagerPortOut.getTeachers] - Executar a chamada da API de TeacherManagement");
        var teacherListOutput = instructorManagerPortOut.getTeachers(authorization, origin);
        log.info("[SERVICE - teacherManagerPortOut.getTeachers] - Chamada da API de TeacherManagement realizada com sucesso");

        sendEventProducerPortOut.sendTeacherInfoEvent(teacherListOutput);

        TeacherListDto teacherListDto = modelMapper.map(teacherListOutput, TeacherListDto.class);
        saveTeachersFromResponse(teacherListDto);

        return teacherListDto;
    }

    @Override
    public TeacherDto getTeacherById(String authorization, String origin, Integer id) {
        log.info("[SERVICE - teacherManagerPortOut.getTeacherById] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.getTeacherById(authorization, origin, id);
        log.info("[SERVICE - teacherManagerPortOut.getTeacherById] - Chamada da API de TeacherManagement realizada com sucesso");

        TeacherDto teacherDto = modelMapper.map(teacherOutput, TeacherDto.class);

        Optional<Teacher> teacherDb = teacherRepository.findById(id);
        if (teacherDb.isEmpty() && teacherDto != null) {
            saveTeacherFromResponse(teacherDto);
        }

        return teacherDto;
    }

    @Override
    @Transactional
    public TeacherDto createTeacher(String authorization, String origin, Instructor instructor) {
        log.info("[SERVICE - teacherManagerPortOut.createTeacher] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.createTeacherResponse(authorization, origin, instructor);
        log.info("[SERVICE - teacherManagerPortOut.createTeacher] - Chamada da API de TeacherManagement realizada com sucesso");

        TeacherDto teacherDto = modelMapper.map(teacherOutput, TeacherDto.class);

        saveTeacherFromResponse(teacherDto);

        return teacherDto;
    }

    @Override
    @Transactional
    public TeacherDto updateTeacher(String authorization, String origin, Instructor instructor, Integer id) {
        log.info("[SERVICE - teacherManagerPortOut.updateTeacher] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.updateTeacherResponse(authorization, origin, instructor, id);
        log.info("[SERVICE - teacherManagerPortOut.updateTeacher] - Chamada da API de TeacherManagement realizada com sucesso");

        TeacherDto teacherDto = modelMapper.map(teacherOutput, TeacherDto.class);

        updateTeacherInDatabase(id, teacherDto);

        return teacherDto;
    }

    @Override
    @Transactional
    public TeacherDto deleteTeacher(String authorization, String origin, Integer id) {
        log.info("[SERVICE - teacherManagerPortOut.deleteTeacher] - Executar a chamada da API de TeacherManagement");
        var teacherOutput = instructorManagerPortOut.deleteTeacherResponse(authorization, origin, id);
        log.info("[SERVICE - teacherManagerPortOut.deleteTeacher] - Chamada da API de TeacherManagement realizada com sucesso");

        TeacherDto teacherDto = modelMapper.map(teacherOutput, TeacherDto.class);

        teacherRepository.findById(id).ifPresent(teacherRepository::delete);

        return teacherDto;
    }

    @Transactional
    protected void saveTeachersFromResponse(TeacherListDto teacherListDto) {
        try {
            if (teacherListDto != null && teacherListDto.getTeachers() != null) {
                for (TeacherDto teacherDto : teacherListDto.getTeachers()) {
                    saveTeacherFromResponse(teacherDto);
                }
            }
        } catch (Exception e) {
            log.error("Erro ao salvar professores no banco de dados", e);
        }
    }

    @Transactional
    protected void saveTeacherFromResponse(TeacherDto teacherDto) {
        try {
            if (teacherDto == null) {
                log.warn("[SERVICE - teacherManagerPortOut.saveTeacherFromResponse] - TeacherDto recebido é nulo, não será salvo");

                return;
            }

            Teacher teacher = new Teacher();
            teacher.setId(teacherDto.getId());
            teacher.setName(teacherDto.getName());

            Salary salary = new Salary();
            if (teacherDto.getSalary() != null) {
                salary.setAmount(teacherDto.getSalary().getAmount());
                salary.setCurrency(teacherDto.getSalary().getCurrency());
            }
            salaryRepository.save(salary);

            Subject subject = new Subject();
            if (teacherDto.getSubject() != null) {
                subject.setName(teacherDto.getSubject().getName());
            }
            subjectRepository.save(subject);

            teacher.setSalary(salary);
            teacher.setSubject(subject);

            teacherRepository.save(teacher);

            log.info("[SERVICE - teacherManagerPortOut.saveTeacherFromResponse] - Professor salvo com sucesso: {}", teacher.getName());

        } catch (Exception e) {
            log.error("[SERVICE - teacherManagerPortOut.saveTeacherFromResponse] - Erro ao salvar professor", e);
        }
    }

    @Transactional
    protected void updateTeacherInDatabase(Integer id, TeacherDto teacherDto) {
        try {
            if (teacherDto == null) {
                log.warn("[SERVICE - teacherManagerPortOut.updateTeacherInDatabase] - TeacherDto recebido é nulo, não será atualizado");
                return;
            }

            Teacher teacher = teacherRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + id));

            teacher.setName(teacherDto.getName());

            Salary salary = teacher.getSalary();
            if (salary == null) {
                salary = new Salary();
                teacher.setSalary(salary);
            }

            if (teacherDto.getSalary() != null) {
                salary.setAmount(teacherDto.getSalary().getAmount());
                salary.setCurrency(teacherDto.getSalary().getCurrency());
            }
            salaryRepository.save(salary);

            Subject subject = teacher.getSubject();
            if (subject == null) {
                subject = new Subject();
                teacher.setSubject(subject);
            }

            if (teacherDto.getSubject() != null) {
                subject.setName(teacherDto.getSubject().getName());
            }
            subjectRepository.save(subject);

            teacherRepository.save(teacher);

            log.info("[SERVICE - teacherManagerPortOut.updateTeacherInDatabase] - Professor atualizado com sucesso: {}", teacher.getName());

        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("[SERVICE - teacherManagerPortOut.updateTeacherInDatabase] - Erro ao atualizar professor", e);
        }
    }
}