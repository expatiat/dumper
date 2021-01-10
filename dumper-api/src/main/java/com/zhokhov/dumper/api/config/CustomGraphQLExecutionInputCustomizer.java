/*
 * Copyright 2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhokhov.dumper.api.config;

import edu.umd.cs.findbugs.annotations.Nullable;
import graphql.ExecutionInput;
import graphql.GraphQLContext;
import io.micronaut.configuration.graphql.GraphQLExecutionInputCustomizer;
import io.micronaut.context.annotation.Primary;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

import static com.zhokhov.jambalaya.micronaut.graphql.GraphQLContextHttpUtils.setRequest;
import static com.zhokhov.jambalaya.micronaut.graphql.GraphQLContextHttpUtils.setResponse;

@Singleton
@Primary
public class CustomGraphQLExecutionInputCustomizer implements GraphQLExecutionInputCustomizer {

    @Override
    public Publisher<ExecutionInput> customize(ExecutionInput executionInput, HttpRequest httpRequest,
                                               @Nullable MutableHttpResponse<String> httpResponse) {
        GraphQLContext graphQLContext = (GraphQLContext) executionInput.getContext();

        setRequest(graphQLContext, httpRequest);
        setResponse(graphQLContext, httpResponse);

        return Publishers.just(executionInput);
    }

}
