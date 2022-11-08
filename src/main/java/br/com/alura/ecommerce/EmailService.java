package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService(EmailService.class.getSimpleName(),"ECOMMERCE_SEND_EMAIL", emailService::parse)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> recorde) {
        System.out.println("Sending email, checking for fraud");
        System.out.println(recorde.key());
        System.out.println(recorde.value());
        System.out.println(recorde.partition());
        System.out.println(recorde.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sent email");
    }
}
