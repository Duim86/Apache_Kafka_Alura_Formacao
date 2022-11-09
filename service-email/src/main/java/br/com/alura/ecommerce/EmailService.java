package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EmailService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var emailService = new EmailService();
        try(var service = new KafkaService<>(EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Message<String>> consumerRecord) {
        System.out.println("------------------------------------------");
        System.out.println("Send email");
        System.out.println(consumerRecord.key());
        System.out.println(consumerRecord.value());
        System.out.println(consumerRecord.partition());
        System.out.println(consumerRecord.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }
}
