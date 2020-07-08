package com.example.gateway;

import com.example.api.ServiceApi;
import io.micronaut.http.client.annotation.Client;

@Client("/service")
public interface ServiceApiClient extends ServiceApi {
}
