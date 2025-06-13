package dev.jesus.component_detail_service.config;

import dev.jesus.component_detail_service.util.converter.JsonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.List;

@Configuration
public class R2dbcConfig {

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, List.of(
                new JsonConverter.ObjetToJsonConverter(),
                new JsonConverter.JsonToObjetConverter()
        ));
    }
}
