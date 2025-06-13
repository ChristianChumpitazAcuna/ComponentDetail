package dev.jesus.component_detail_service.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @WritingConverter
    public static class ObjetToJsonConverter implements Converter<ComponentProperties, String> {
        @Override
        public String convert(ComponentProperties source) {
            try {
                return objectMapper.writeValueAsString(source);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Could not convert source Map to JSON", e);
            }
        }
    }

    @ReadingConverter
    public static class JsonToObjetConverter implements Converter<String, ComponentProperties> {

        @Override
        public ComponentProperties convert(String source) {
            try {
                return objectMapper.readValue(source, ComponentProperties.class);
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not convert source JSON to Map", e);
            }
        }
    }


}
