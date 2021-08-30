package org.thraex.boardroom.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @author 鬼王
 * @date 2021/08/30 16:10
 */
@Configuration
public class TemporalFormatConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer builder() {
        final int capacity = 3;
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return builder -> builder
                .serializersByType(new HashMap<Class<?>, JsonSerializer<?>>(capacity) {{
                    put(LocalDate.class, new LocalDateSerializer(date));
                    put(LocalTime.class, new LocalTimeSerializer(time));
                    put(LocalDateTime.class, new LocalDateTimeSerializer(dateTime));
                }})
                .deserializersByType(new HashMap<Class<?>, JsonDeserializer<?>>(capacity) {{
                    put(LocalDate.class, new LocalDateDeserializer(date));
                    put(LocalTime.class, new LocalTimeDeserializer(time));
                    put(LocalDateTime.class, new LocalDateTimeDeserializer(dateTime));
                }});
    }

}
