package com.example.demo.config;

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
        FieldComplexityCalculator calculator = (env, childComplexity) -> {
            String parentType = env.getParentType().getName();
            String fieldName = env.getField().getName();
            String key = parentType + "." + fieldName;

            Map<String, Integer> weights = Map.of(
                    "Query.users", 1,
                    "User.merchant", 3,
                    "Merchant.id", 1,
                    "Mutation.createUser", 5,
                    "Mutation.deleteUser", 10
            );

            int weight = weights.getOrDefault(key, 1);
            return weight + childComplexity;
        };

        return new ChainedInstrumentation(List.of(
                new SafeDepthInstrumentation(3),
                new SafeComplexityInstrumentation(1, calculator)
        ));
    }
}

