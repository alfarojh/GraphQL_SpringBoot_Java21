package com.example.demo.config;

import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GraphQLConfig {

    @Bean
    public Instrumentation instrumentation() {
        return new ChainedInstrumentation(List.of(
                new SafeDepthInstrumentation(3),
                new SafeComplexityInstrumentation(1)
        ));

    }
}

