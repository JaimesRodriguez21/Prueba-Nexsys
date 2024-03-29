package com.nexsys.config;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }
}
