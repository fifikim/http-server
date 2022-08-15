package server.router;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;
import server.response.Response;

public class RequestRouterTest {
  @Test
  public void processesCorrectResponseForSimpleGet() throws IOException {
    Request request = new Request(Method.GET, Path.SIMPLE_GET, null, null);

    Response expectedResponse = TestHelpers.simpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForSimpleGetWithBody() throws IOException {
    Request request = new Request(Method.GET, Path.SIMPLE_GET_WITH_BODY, null, null);

    Response expectedResponse = TestHelpers.simpleGetWithBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void processesCorrectResponseForUnknownRoute() throws IOException {
    Request request = new Request(Method.GET, null, null, null);

    Response expectedResponse = TestHelpers.notFoundResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForInvalidMethod() throws IOException {
    Request request = new Request(null, Path.SIMPLE_GET, null, null);

    Response expectedResponse = TestHelpers.badRequestResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsMethodNotAllowedForEachRoute() throws IOException {
    HashMap<Path, Method> badRequests = TestHelpers.badRequests();

    for (Map.Entry<Path, Method> mapElement : badRequests.entrySet()) {
      Method method = mapElement.getValue();
      Path path = mapElement.getKey();

      Request request = new Request(method, path, null, null);
      Response response = new RequestRouter().getResponse(request);
      String startLine = response.startLine();

      assertEquals("HTTP/1.1 405 Method Not Allowed", startLine);
    }
  }

  @Test
  public void returnsCorrectResponseForHeadRequestToSimpleGet() throws IOException {
    Request request = new Request(Method.HEAD, Path.SIMPLE_GET,  null, null);

    Response expectedResponse = TestHelpers.headRequestToSimpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForHeadRequestToHeadRequest() throws IOException {
    Request request = new Request(Method.HEAD, Path.HEAD_REQUEST,  null, null);

    Response expectedResponse = TestHelpers.headRequestToHeadResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForPostRequestToEchoBody() throws IOException {
    Request request = new Request(Method.POST, Path.ECHO_BODY, null, "test message");

    Response expectedResponse = TestHelpers.echoBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForGetRequestToRedirect() throws IOException {
    HashMap<String, String> headers = TestHelpers.mappedHeaders();
    Request request = new Request(Method.GET, Path.REDIRECT, headers, null);

    Response expectedResponse = TestHelpers.redirectResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForOptionsRequestToMethodOptions() throws IOException {
    Request request = new Request(Method.OPTIONS, Path.METHOD_OPTIONS, null, null);

    Response expectedResponse = TestHelpers.methodOptionsResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForOptionsRequestToMethodOptions2() throws IOException {
    Request request = new Request(Method.OPTIONS, Path.METHOD_OPTIONS2, null, null);

    Response expectedResponse = TestHelpers.methodOptions2Response();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForGetRequestToHtmlResponse() throws IOException {
    Request request = new Request(Method.GET, Path.HTML_RESPONSE, null, null);

    Response expectedResponse = TestHelpers.htmlResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForGetRequestToJsonResponse() throws IOException {
    Request request = new Request(Method.GET, Path.JSON_RESPONSE, null, null);

    Response expectedResponse = TestHelpers.jsonResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForGetRequestToTextResponse() throws IOException {
    Request request = new Request(Method.GET, Path.TEXT_RESPONSE, null, null);

    Response expectedResponse = TestHelpers.textResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForGetRequestToXmlResponse() throws IOException {
    Request request = new Request(Method.GET, Path.XML_RESPONSE, null, null);

    Response expectedResponse = TestHelpers.xmlResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForHealthCheckRequest() throws IOException {
    Request request = new Request(Method.GET, Path.HEALTH_CHECK, null, null);

    Response expectedResponse = TestHelpers.healthCheckResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForKittehJpg() throws IOException {
    Request request = new Request(Method.GET, Path.KITTEH_JPG, null, null);

    Response expectedResponse = TestHelpers.kittehJpgResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForDoggoPng() throws IOException {
    Request request = new Request(Method.GET, Path.DOGGO_PNG, null, null);

    Response expectedResponse = TestHelpers.doggoPngResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }

  @Test
  public void returnsCorrectResponseForKissesGif() throws IOException {
    Request request = new Request(Method.GET, Path.KISSES_GIF, null, null);

    Response expectedResponse = TestHelpers.kissesGifResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse.startLine(), actualResponse.startLine());
    assertEquals(expectedResponse.headers(), actualResponse.headers());
    assertEquals(Arrays.toString(expectedResponse.body()), Arrays.toString(actualResponse.body()));
  }
}
