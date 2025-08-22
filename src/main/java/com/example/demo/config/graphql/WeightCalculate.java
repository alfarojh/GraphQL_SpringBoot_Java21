package com.example.demo.config.graphql;

import graphql.analysis.FieldComplexityCalculator;
import graphql.analysis.FieldComplexityEnvironment;
import graphql.language.IntValue;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLDirective;

public class WeightCalculate implements FieldComplexityCalculator {
    @Override
    public int calculate(FieldComplexityEnvironment env, int childComplexity) {
        GraphQLDirective directive = env.getFieldDefinition().getDirective("complexity");

        if (directive != null) {
            GraphQLArgument arg = directive.getArgument("value");
            Object astValue = arg.toAppliedArgument()
                    .getArgumentValue()
                    .getValue(); // AST node

            if (astValue instanceof IntValue intValue) {
                return intValue.getValue().intValue() + childComplexity;
            }
        }

        return 1 + childComplexity;
    }
}
