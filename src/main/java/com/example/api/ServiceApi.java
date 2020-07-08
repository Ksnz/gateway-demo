package com.example.api;

import com.example.model.Request;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import reactor.core.publisher.Mono;

public interface ServiceApi {

  @Post(value = "/b")
  HttpResponse<String> blocking(@Body Request request);

  @Post(value = "/r")
  Mono<HttpResponse<String>> reactive(@Body Request request);
}
