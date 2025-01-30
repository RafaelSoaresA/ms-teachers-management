package com.example.ms.teachers.management.infraestructure.event.kafka.mapper;

import com.example.ms.teachers.management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.kafka.contracts.Event;
import com.example.ms.teachers.management.kafka.contracts.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class KafkaMapperTest {

    private KafkaMapper kafkaMapper;
    private ModelMapper modelMapper;

    private final TeacherDtoOutput teacherDtoOutputPhysics = new TeacherDtoOutput();
    private final TeacherDtoOutput teacherDtoOutputMathematics = new TeacherDtoOutput();
    private final SubjectDtoOutput subjectDtoOutputPhysics = new SubjectDtoOutput();
    private final SubjectDtoOutput subjectDtoOutputMathematics = new SubjectDtoOutput();
    private final TeacherDtoOutput teacherDtoOutput = new TeacherDtoOutput();
    private final SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();

    @BeforeEach
    public void setup() {
        kafkaMapper = new KafkaMapper();
        modelMapper = new ModelMapper();

        teacherDtoOutputPhysics.setName("Rafael");
        subjectDtoOutputPhysics.setName("Physics");
        teacherDtoOutputPhysics.setSubject(subjectDtoOutputPhysics);

        teacherDtoOutputPhysics.setName("Lucas");
        subjectDtoOutputMathematics.setName("Mathematics");
        teacherDtoOutputMathematics.setSubject(subjectDtoOutputMathematics);

        teacherDtoOutput.setName("João");
        subjectDtoOutput.setName("Portuguese");
        teacherDtoOutput.setSubject(subjectDtoOutput);

    }

    @Test
    @DisplayName("Deve criar um evento com professores de Física e Matemática")
    public void createEventWithPhysicsAndMathematicsTeachers() {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        teacherList.setTeachers(List.of(
                teacherDtoOutputPhysics,
                teacherDtoOutputMathematics
        ));

        Event event = kafkaMapper.createEvent(teacherList);

        assertNotNull(event.getUuid());
        assertNotNull(event.getCreatedDate());
        assertEquals(2, event.getTeachers().size());
    }

    @Test
    @DisplayName("Deve criar um evento sem professores de Física e Matemática")
    public void createEventWithoutPhysicsAndMathematicsTeachers() {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        teacherList.setTeachers(List.of(
                teacherDtoOutput,
                teacherDtoOutput
        ));

        Event event = kafkaMapper.createEvent(teacherList);

        assertNotNull(event.getUuid());
        assertNotNull(event.getCreatedDate());
        assertEquals(0, event.getTeachers().size());
    }

    @Test
    @DisplayName("Deve criar um evento com lista de professores vazia")
    public void createEventWithEmptyTeacherList() {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        teacherList.setTeachers(List.of());

        Event event = kafkaMapper.createEvent(teacherList);

        assertNotNull(event.getUuid());
        assertNotNull(event.getCreatedDate());
        assertEquals(0, event.getTeachers().size());
    }

}