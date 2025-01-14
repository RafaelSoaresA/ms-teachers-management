package com.example.ms.teachers.management.infraestructure.event.kafka;

import com.example.ms.teachers.management.core.port.out.SendEventProducerPortOut;
import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.event.kafka.mapper.KafkaMapper;
import com.example.ms.teachers.management.kafka.contracts.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendEventProducer implements SendEventProducerPortOut {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    private final KafkaMapper kafkaMapper;

    @Value("${TOPIC_TEACHER_INFO:teacher-info-topic}")
    private String topicName;
    @Override
    public void sendTeacherInfoEvent(TeacherListDtoOutput teacherList) {
        Event event = kafkaMapper.createEvent(teacherList);

        this.writeEventOnTopic(event);
    }

    private void writeEventOnTopic(Event event) {
        try{
            kafkaTemplate.send(produceIn(event));
            log.info("[KAKFA PRODUCER - TEACHER INFO] - EVENTO POSTADO" + event);
        }catch (RuntimeException e){
            log.error("[KAKFA PRODUCER - TEACHER INFO] - ERRO");
            log.error("[KAFKA PRODUCER - EMPLOYEE INFO] - ERRO: " + e.getMessage());
            log.error("[KAFKA PRODUCER - EMPLOYEE INFO] - ERRO STACK: " + e.getStackTrace());
            throw e;
        }
    }

    private ProducerRecord<String, Event> produceIn(Event event) {
        return new ProducerRecord<>(this.topicName, event);
    }
}
