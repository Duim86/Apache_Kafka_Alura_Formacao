package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();
        try(var service = new KafkaService(FraudDetectorService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER", fraudService::parse)) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> records) {
            System.out.println("Processing new order, checking for fraud");
            System.out.println(records.key());
            System.out.println(records.value());
            System.out.println(records.partition());
            System.out.println(records.offset());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
