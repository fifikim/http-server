package server.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;

public class RequestParserTest {
  @Test
  public void parsesRequestMethod() {
    String testRequest = TestHelpers.stringRequest();
    Method method = new RequestParser(testRequest).method();

    assertEquals(Method.GET, method);
  }

  @Test
  public void parsesInvalidMethodAsNullMethod() {
    String testRequest = TestHelpers.stringRequestWithBadMethod();
    Method method = new RequestParser(testRequest).method();

    assertNull(method);
  }

  @Test
  public void parsesRequestPath() {
    String testRequest = TestHelpers.stringRequest();
    Path path = new RequestParser(testRequest).path();

    assertEquals(Path.SIMPLE_GET, path);
  }

  @Test
  public void parsesUnknownRouteAsNullPath() {
    String testRequest = TestHelpers.stringRequestWithBadRoute();
    Path path = new RequestParser(testRequest).path();

    assertNull(path);
  }

  @Test
  public void parsesRequestHeader() {
    String testRequest = TestHelpers.stringRequestWithHeader();
    HashMap<String, String> header = new RequestParser(testRequest).headers();

    assertEquals("*/*", header.get("Accept"));
  }

  @Test
  public void parsesMultipleHeadersAsHashMapPairs() {
    String testRequest = TestHelpers.stringRequestWithMultipleHeaders();
    HashMap<String, String> headers = new RequestParser(testRequest).headers();

    assertEquals("*/*", headers.get("Accept"));
    assertEquals("0.0.0.0:5000", headers.get("Host"));
    assertEquals("gzip, deflate, br", headers.get("Accept-Encoding"));
    assertEquals("keep-alive", headers.get("Connection"));
  }
}
