package com.example.demo.config;

import graphql.ExecutionResult;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters;

public class SafeComplexityInstrumentation extends MaxQueryComplexityInstrumentation {
    public SafeComplexityInstrumentation(int maxComplexity) {
        super(maxComplexity);
    }

    @Override
    public InstrumentationContext<ExecutionResult> beginExecuteOperation(
            InstrumentationExecuteOperationParameters parameters,
            InstrumentationState state
    ) {
        String query = parameters.getExecutionContext().getExecutionInput().getQuery();
        if (query != null && query.contains("__schema")) {
            // Skip complexity check for introspection
            return SimpleInstrumentationContext.noOp();
        }
        return super.beginExecuteOperation(parameters, state);
    }

}
