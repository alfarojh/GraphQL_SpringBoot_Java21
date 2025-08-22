package com.example.demo.config;

import com.example.demo.config.graphql.SafeComplexityInstrumentation;
import com.example.demo.config.graphql.SafeDepthInstrumentation;
import com.example.demo.config.graphql.WeightCalculate;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GraphQLConfig {
    /**
     * Configures GraphQL instrumentation to limit query complexity and depth.
     *
     * @return an Instrumentation instance with safe depth and complexity settings.
     */

    @Bean
    public Instrumentation instrumentation() {
        WeightCalculate calculator = new WeightCalculate();

        return new ChainedInstrumentation(List.of(
                new SafeDepthInstrumentation(5),
                new SafeComplexityInstrumentation(100, calculator)
        ));
    }
}

