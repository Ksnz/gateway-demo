There are some weird conversion behaviors when I try pass HttpResponse through gateway chain.
I have a REST interface api. 
The api could return 400 or 402 with empty body, depending on params, so API looks like:
```
  @Post(value = "/r")
  Mono<HttpResponse<String>> reactive(@Body Request request);
```
I have a service which implements it.
I have a gateway service which uses the API to make a client, and a controller to chain calls to the service via the client.

**Direct call to service**
```
POST http://localhost:8080/service/b

HTTP/1.1 200 OK
Date: Wed, 8 Jul 2020 13:15:17 GMT
connection: keep-alive
transfer-encoding: chunked

<Response body is empty>

Response code: 200 (OK); Time: 354ms; Content length: 0 bytes
```
**Call to service through gateway**
```
POST http://localhost:8080/gateway/b

HTTP/1.1 200 OK
Date: Wed, 8 Jul 2020 13:20:29 GMT
Content-Type: application/json
content-length: 276
connection: keep-alive

{
  "status": "OK",
  "headers": {
    "Date": "Wed, 8 Jul 2020 13:20:29 GMT",
    "connection": "close"
  },
  "nativeResponse": {
    "protocolVersion": {
      "keepAliveDefault": true
    },
    "decoderResult": {
      "success": true,
      "finished": true,
      "failure": false
    }
  },
  "stream": false,
  "contentLength": -1,
  "characterEncoding": "UTF-8"
}

Response code: 200 (OK); Time: 429ms; Content length: 276 bytes
```
### Steps to Reproduce

1. Make an API interface with various  return types
2. Make a "service" controller that implements the API
3. Make a declarative client that extends the API and using "service" controller path.
4. Make a "gateway" controller that implements the API by chaining calls to the client.
5. Make sure call to "service" returns same as call to "gateway"

### Expected Behaviour
Call to gateway returns same body as call to service.
### Actual Behaviour
Call to gateway returns JSON serialized FullNettyClientHttpResponse
### Environment Information

- **Operating System**: Win 10
- **Micronaut Version:** 2.0.0
- **JDK Version:** 1.8

### Example Application

https://github.com/Ksnz/gateway-demo
You can run it as an app or just run tests.

### Some notes
In 1.3.6 same code works almost correctly, except that gateway call returns 
```
Content-lenght: 4
null
```
I found that the reason is wrapping Optional in Optional in [ClientResponseConverter](https://docs.micronaut.io/latest/api/io/micronaut/http/client/converters/ClientResponseConverter.html)


