package com.example;

import com.example.api.ServiceApi;
import com.example.model.Request;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
public class GatewayTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(GatewayTest.class);

  @Inject
  TestGatewayApiClient gatewayClient;

  @Inject
  TestServiceApiClient serviceClient;

  @Test
  void testBodySameBlocking() {
    Request request = new Request();
    request.setFoo("bar");
    HttpResponse<?> serviceResponse = serviceClient.blocking(request);
    HttpResponse<?> gatewayResponse = gatewayClient.blocking(request);
    Assertions.assertEquals(serviceResponse.getStatus(), gatewayResponse.getStatus());
    Assertions.assertEquals(serviceResponse.body(), gatewayResponse.body());
  }

  @Test
  void testBodySameReactiveResponse() {
    Request request = new Request();
    request.setFoo("bar");
    HttpResponse<?> serviceResponse = serviceClient.reactive(request)
                                          .block();
    HttpResponse<?> gatewayResponse = gatewayClient.reactive(request)
                                          .block();
    Assertions.assertEquals(serviceResponse.getStatus(), gatewayResponse.getStatus());
    Assertions.assertEquals(serviceResponse.body(), gatewayResponse.body());
  }

  @Client("/gateway")
  interface TestGatewayApiClient extends ServiceApi {

  }

  @Client("/service")
  interface TestServiceApiClient extends ServiceApi {

  }
}
