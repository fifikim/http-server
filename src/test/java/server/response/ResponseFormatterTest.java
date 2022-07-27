package server.response;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;

public class ResponseFormatterTest {
  @Test
  public void formatsStartLine() {
    String expectedStartLine = "HTTP/1.1 200 OK\r\n";
    String actualStartLine = ResponseFormatter.startLine("HTTP/1.1", "200 OK");

    assertEquals(expectedStartLine, actualStartLine);
  }

  @Test
  public void formatsHeaders() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add("Allow: GET, HEAD, OPTIONS");
    headers.add("Content-Length: 143");

    String expectedHeaders = "Allow: GET, HEAD, OPTIONS\r\nContent-Length: 143\r\n";
    String actualHeaders = ResponseFormatter.formatHeaders(headers);

    assertEquals(expectedHeaders, actualHeaders);
  }

  @Test
  public void formatsBody() {
    String expectedBody = "\r\nHello world";
    String actualBody = ResponseFormatter.formatBody("Hello world");

    assertEquals(expectedBody, actualBody);
  }
}
