package ru.den.projectpractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Путь, для которого настраивается CORS
                .allowedOrigins("http://localhost:3000") // Указывает, с каких источников разрешены запросы
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные методы HTTP запросов
                .allowedHeaders("*") // Разрешенные заголовки
                .allowCredentials(true); // Разрешает отправку учетных данных
    }
}
