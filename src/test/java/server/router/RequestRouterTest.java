package server.router;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;
import server.response.Response;

public class RequestRouterTest {
  @Test
  public void processesCorrectResponseForSimpleGet() {
    Request request = new Request(Method.GET, Path.SIMPLE_GET, "");

    Response expectedResponse = TestHelpers.simpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForSimpleGetWithBody() {
    Request request = new Request(Method.GET, Path.SIMPLE_GET_WITH_BODY, "");

    Response expectedResponse = TestHelpers.simpleGetWithBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForUnknownRoute() {
    Request request = new Request(Method.GET, null, "");

    Response expectedResponse = TestHelpers.notFoundResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForInvalidMethod() {
    Request request = new Request(null, Path.SIMPLE_GET, "");

    Response expectedResponse = TestHelpers.badRequestResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void getsCorrectResponseForNotAllowedGetRequest() {
    Request request = new Request(Method.GET, Path.HEAD_REQUEST, "");

    Response expectedResponse = TestHelpers.notAllowedResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }
}