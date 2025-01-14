package ms.teachers.management.consumer.teacher.api.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.teachers.management.consumer.teacher.kafka.contracts.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TeacherEventConsumerImpl implements TeacherEventConsumer{

    @Override
    public void consume(ConsumerRecord<String, Event> event) {
        log.info("[CONSUMER] - [EmployeeEventConsumer.consume] - Evento consumido: {}", event);
    }
}
