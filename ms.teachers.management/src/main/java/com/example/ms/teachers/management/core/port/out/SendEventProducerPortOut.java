package com.example.ms.teachers.management.core.port.out;

import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;

public interface SendEventProducerPortOut {

    void sendTeacherInfoEvent(TeacherListDtoOutput teacherListDtoOutput);
}
