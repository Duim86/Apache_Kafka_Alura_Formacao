package br.com.alura.ecommerce;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageAdapter<T> implements JsonSerializer<Message<T>>, JsonDeserializer<Message<T>> {
    @Override
    public JsonElement serialize(Message message, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", message.payload().getClass().getName());
        obj.add("payload", context.serialize(message.payload()));
        obj.add("correlationId", context.serialize(message.id()));
        return obj;
    }

    @Override
    public Message deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        var obj = json.getAsJsonObject();
        var payloadType = obj.get("type").getAsString();
        var correlationId = (CorrelationId) context.deserialize(obj.get("correlationId"), CorrelationId.class);
        try {
            // maybe you want to use an "accept list"
            var payload = context.deserialize(obj.get("payload"), Class.forName(payloadType));
            return new Message(correlationId, payload);
        } catch (ClassNotFoundException e) {
            // you might want to deal with this exception
            throw new JsonParseException(e);
        }
    }
}
