package br.com.alura.ecommerce;

public record Message<T>(CorrelationId id, T payload) {

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", payload=" + payload +
                '}';
    }
}
