package com.example.service;

import com.example.api.ServiceApi;
import com.example.model.Request;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import reactor.core.publisher.Mono;

/**
 * Just response OK
 */
@Controller("/service")
public class ServiceController implements ServiceApi {
  @Override
  public HttpResponse<String> blocking(Request request) {
    return HttpResponse.ok().body("hi");
  }

  @Override
  public Mono<HttpResponse<String>> reactive(Request request) {
    return Mono.fromCallable(HttpResponse::ok);
  }
}
