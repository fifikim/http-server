package server.request;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import server.TestHelpers;

public class RequestParserTest {
  @Test
  public void parsesRequestWithNoBody() {
    String testRequest = TestHelpers.stringRequest();
    Request request = new RequestParser().parse(testRequest);

    assertEquals("GET", request.method());
    assertEquals("/simple_get", request.path());
    assertEquals("HTTP/1.1", request.protocol());
    assertEquals("", request.body());
  }

  @Test
  public void parsesRequestWithBody() {
    String testRequest = TestHelpers.stringRequestWithBody();
    Request request = new RequestParser().parse(testRequest);

    assertEquals("POST", request.method());
    assertEquals("/echo_body", request.path());
    assertEquals("HTTP/1.1", request.protocol());
    assertEquals("Hello world", request.body());
  }
}
