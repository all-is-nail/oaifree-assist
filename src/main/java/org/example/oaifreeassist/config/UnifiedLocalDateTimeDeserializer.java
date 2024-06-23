package org.example.oaifreeassist.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UnifiedLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter formatterWithMinutes = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
        String text = p.getText();
        try {
            return LocalDateTime.parse(text, formatterWithTime);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(text, formatterWithMinutes);
            } catch (DateTimeParseException e2) {
                try {
                    return LocalDateTime.parse(text + "T00:00:00", formatterWithTime);
                } catch (DateTimeParseException e3) {
                    throw new RuntimeException("Failed to parse date: " + text, e3);
                }
            }
        }
    }
}
