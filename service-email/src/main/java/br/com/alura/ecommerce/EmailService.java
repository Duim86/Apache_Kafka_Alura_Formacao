package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.ConsumerService;
import br.com.alura.ecommerce.consumer.ServiceRunner;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;


public class EmailService implements ConsumerService<String> {
    public static void main(String[] args) {
        new ServiceRunner<>(EmailService::new).start(5);
    }
    public String getConsumerGroup() {
        return EmailService.class.getSimpleName();
    }

    public void parse(ConsumerRecord<String, Message<String>> consumerRecord) {
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

    public String getTopic(){
        return "ECOMMERCE_SEND_EMAIL";
    }

}
