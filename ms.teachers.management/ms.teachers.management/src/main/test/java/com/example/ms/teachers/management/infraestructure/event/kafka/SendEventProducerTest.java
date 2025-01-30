package com.example.ms.teachers.management.infraestructure.event.kafka;

import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.event.kafka.mapper.KafkaMapper;
import com.example.ms.teachers.management.kafka.contracts.Event;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendEventProducerTest {

    @Mock
    private KafkaTemplate<String, Event> kafkaTemplate;

    @Mock
    private KafkaMapper kafkaMapper;

    @InjectMocks
    private SendEventProducer sendEventProducer;

    @Test
    @DisplayName("Deve enviar evento de informações do professor com sucesso")
    void sendTeacherInfoEvent_success() {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        Event event = new Event();
        when(kafkaMapper.createEvent(teacherList)).thenReturn(event);

        sendEventProducer = new SendEventProducer(kafkaTemplate, kafkaMapper);
        ReflectionTestUtils.setField(sendEventProducer, "topicName", "teacher-info-topic");

        sendEventProducer.sendTeacherInfoEvent(teacherList);

        verify(kafkaTemplate, times(1)).send(any(ProducerRecord.class));
    }

    @Test
    @DisplayName("Deve lançar RuntimeException ao enviar evento de informações do professor")
    void sendTeacherInfoEvent_runtimeException() {
        TeacherListDtoOutput teacherList = new TeacherListDtoOutput();
        Event event = new Event();
        when(kafkaMapper.createEvent(teacherList)).thenReturn(event);

        sendEventProducer = new SendEventProducer(kafkaTemplate, kafkaMapper);
        ReflectionTestUtils.setField(sendEventProducer, "topicName", "teacher-info-topic");

        doThrow(new RuntimeException("Kafka error")).when(kafkaTemplate).send(any(ProducerRecord.class));

        assertThrows(RuntimeException.class, () -> sendEventProducer.sendTeacherInfoEvent(teacherList));

        verify(kafkaTemplate, times(1)).send(any(ProducerRecord.class));
    }
}