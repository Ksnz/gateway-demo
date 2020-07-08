package com.example.gateway;

import com.example.api.ServiceApi;
import com.example.model.Request;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import javax.inject.Inject;
import reactor.core.publisher.Mono;

@Controller("/gateway")
public class ServiceApiGatewayController implements ServiceApi {

  @Inject
  ServiceApiClient client;

  @Override
  public HttpResponse<String> blocking(Request request) {
    return client.blocking(request);
  }

  @Override
  public Mono<HttpResponse<String>> reactive(Request request) {
    return client.reactive(request);
  }
}
