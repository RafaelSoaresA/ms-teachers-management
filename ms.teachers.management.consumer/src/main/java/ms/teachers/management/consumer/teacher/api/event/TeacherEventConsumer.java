package ms.teachers.management.consumer.teacher.api.event;


import ms.teachers.management.consumer.teacher.kafka.contracts.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public interface TeacherEventConsumer {

    @KafkaListener(topics = "${TOPIC_TEACHER_INFO:teacher-info-topic}")
    void consume(ConsumerRecord<String, Event> event);
}
