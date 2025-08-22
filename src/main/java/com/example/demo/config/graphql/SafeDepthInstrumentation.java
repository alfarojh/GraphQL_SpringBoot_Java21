package com.example.demo.config.graphql;

import graphql.ExecutionResult;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimplePerformantInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters;

public class SafeDepthInstrumentation extends SimplePerformantInstrumentation {
    private final int maxDepth;

    public SafeDepthInstrumentation(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public InstrumentationContext<ExecutionResult> beginExecuteOperation(InstrumentationExecuteOperationParameters parameters, InstrumentationState state) {
        String query = parameters
                .getExecutionContext()
                .getExecutionInput()
                .getQuery();
        boolean isIntrospection = query != null && (query.contains("__schema") || query.contains("__type"));

        if (isIntrospection) {
            return super.beginExecuteOperation(parameters, state); // introspection lolos
        }

        return new MaxQueryDepthInstrumentation(maxDepth).beginExecuteOperation(parameters, state);

    }


}
