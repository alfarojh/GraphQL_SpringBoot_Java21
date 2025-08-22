package com.example.demo.config;

import graphql.execution.instrumentation.Instrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public Instrumentation instrumentation() {
        return new SafeDepthInstrumentation(3);
    }
}
