package com.example.demo.config;

import com.example.demo.config.graphql.SafeComplexityInstrumentation;
import com.example.demo.config.graphql.SafeDepthInstrumentation;
import com.example.demo.config.graphql.WeightCalculate;
import graphql.analysis.FieldComplexityCalculator;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class GraphQLConfig {

    @Bean
    public Instrumentation instrumentation() {
        WeightCalculate calculator = new WeightCalculate();

        return new ChainedInstrumentation(List.of(
                new SafeDepthInstrumentation(3),
                new SafeComplexityInstrumentation(1, calculator)
        ));
    }
}

