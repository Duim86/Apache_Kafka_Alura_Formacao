package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var logService = new LogService();
        try(var service = new KafkaService<>(LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Message<String>> recorde) {
        System.out.println("LOG " + recorde.topic());
        System.out.println(recorde.key());
        System.out.println(recorde.value());
        System.out.println(recorde.partition());
        System.out.println(recorde.offset());
    }
}
