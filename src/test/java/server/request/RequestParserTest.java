package server.request;

import static org.junit.Assert.assertEquals;

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
  public void parsesRequestWithBody() {
    String testRequest = TestHelpers.stringRequestWithBody();
    Request request = new RequestParser().parse(testRequest);

    assertEquals(Method.POST, request.method());
    assertEquals(Path.ECHO_BODY, request.path());
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
