package com.pbtan.movies.inventory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class MovieDeserializer extends JsonDeserializer<Movie> {

//TODO int null
    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new Movie(node.get("title").textValue(),
                node.get("releaseYear").asInt(),
                node.get("releaseMonth").asInt(),
                node.get("releaseDay").asInt(),
                node.get("category").textValue(),
                node.get("actors").textValue(),
                node.get("description").textValue()
        );
    }
}
