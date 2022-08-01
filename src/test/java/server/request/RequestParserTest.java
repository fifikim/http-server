package server.request;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;

public class RequestParserTest {
  @Test
  public void parsesInvalidMethodAsNullMethod() {
    String testRequest = TestHelpers.stringRequestWithBadMethod();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(null, request.method());
    assertEquals(Path.SIMPLE_GET, request.path());
    assertEquals(null, request.body());
  }

  @Test
  public void parsesUnknownRouteAsNullPath() {
    String testRequest = TestHelpers.stringRequestWithBadRoute();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.GET, request.method());
    assertEquals(null, request.path());
    assertEquals(null, request.body());
  }

  @Test
  public void parsesRequestWithNoBody() {
    String testRequest = TestHelpers.stringRequest();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.GET, request.method());
    assertEquals(Path.SIMPLE_GET, request.path());
    assertEquals(null, request.body());
  }

  @Test
  public void parsesRequestWithStartLineOnly() {
    String testRequest = TestHelpers.stringRequest();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.GET, request.method());
    assertEquals(Path.SIMPLE_GET, request.path());
    assertEquals(Collections.emptyMap(), request.headers());
    assertEquals(null, request.body());
  }

  @Test
  public void parsesRequestWithHeaders() {
    String testRequest = TestHelpers.stringRequestWithHeader();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.GET, request.method());
    assertEquals(Path.SIMPLE_GET, request.path());
    assertEquals("*/*", request.headers().get("Accept"));
    assertEquals(null, request.body());
  }

  @Test
  public void parsesMultipleHeadersAsHashMapPairs() {
    String testRequest = TestHelpers.stringRequestWithMultipleHeaders();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.GET, request.method());
    assertEquals(Path.SIMPLE_GET, request.path());
    assertEquals("*/*", request.headers().get("Accept"));
    assertEquals("localhost:5000", request.headers().get("Host"));
    assertEquals("gzip, deflate, br", request.headers().get("Accept-Encoding"));
    assertEquals("keep-alive", request.headers().get("Connection"));
    assertEquals(null, request.body());
  }

  @Test
  public void parsesRequestWithHeadersAndBody() {
    String testRequest = TestHelpers.stringRequestWithBody();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.POST, request.method());
    assertEquals(Path.ECHO_BODY, request.path());
    assertEquals("11", request.headers().get("Content-Length"));
    assertEquals("Hello world", request.body());
  }

  @Test
  public void parsesRequestWithBodyThatIncludesLineBreaks() {
    String testRequest = TestHelpers.stringRequestWithBodyWithBreaks();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.POST, request.method());
    assertEquals(Path.ECHO_BODY, request.path());
    assertEquals("Hello world\r\n\r\nSecond line\r\n\r\nThird line", request.body());
  }
}
