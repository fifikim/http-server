package server.router;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;
import server.response.Response;

public class RequestRouterTest {
  @Test
  public void processesCorrectResponseForSimpleGet() {
    Request request = new Request(Method.GET, Path.SIMPLE_GET, null, null);

    Response expectedResponse = TestHelpers.simpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForSimpleGetWithBody() {
    Request request = new Request(Method.GET, Path.SIMPLE_GET_WITH_BODY, null, null);

    Response expectedResponse = TestHelpers.simpleGetWithBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForUnknownRoute() {
    Request request = new Request(Method.GET, null, null, null);

    Response expectedResponse = TestHelpers.notFoundResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForInvalidMethod() {
    Request request = new Request(null, Path.SIMPLE_GET, null, null);

    Response expectedResponse = TestHelpers.badRequestResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForNotAllowedGetRequest() {
    Request request = new Request(Method.GET, Path.HEAD_REQUEST, null, null);

    Response expectedResponse = TestHelpers.notAllowedResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForHeadRequestToSimpleGet() {
    Request request = new Request(Method.HEAD, Path.SIMPLE_GET,  null, null);

    Response expectedResponse = TestHelpers.headRequestToSimpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForHeadRequestToHeadRequest() {
    Request request = new Request(Method.HEAD, Path.HEAD_REQUEST,  null, null);

    Response expectedResponse = TestHelpers.headRequestToHeadResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForPostRequestToEchoBody() {
    Request request = new Request(Method.POST, Path.ECHO_BODY, null, "test message");

    Response expectedResponse = TestHelpers.echoBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void returnsCorrectResponseForGetRequestToRedirect() {
    HashMap<String, String> headers = TestHelpers.mappedHeaders();
    Request request = new Request(Method.GET, Path.REDIRECT, headers, null);

    Response expectedResponse = TestHelpers.redirectResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }
}
