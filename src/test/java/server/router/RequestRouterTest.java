package server.router;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import server.TestHelpers;
import server.request.Request;
import server.response.Response;

public class RequestRouterTest {
  @Test
  public void processesCorrectResponseForSimpleGet() {
    Request request = new Request("GET", "/simple_get", "HTTP/1.1", "");

    Response expectedResponse = TestHelpers.simpleGetResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForSimpleGetWithBody() {
    Request request = new Request("GET", "/simple_get_with_body", "HTTP/1.1", "");

    Response expectedResponse = TestHelpers.simpleGetWithBodyResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void processesCorrectResponseForUnknownRoute() {
    Request request = new Request("GET", "/bad_route", "HTTP/1.1", "");

    Response expectedResponse = TestHelpers.notFoundResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void getsCorrectResponseForNotAllowedGetRequest() {
    Request request = new Request("GET", "/head_request", "HTTP/1.1", "");

    Response expectedResponse = TestHelpers.notAllowedResponse();
    Response actualResponse = new RequestRouter().getResponse(request);

    assertEquals(expectedResponse, actualResponse);
  }
}
