package server.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import server.TestHelpers;

public class ResponseFormatterTest {
  @Test
  public void correctlyFormatsResponseWithStartLineHeadersAndBody() {
    Response testResponse = TestHelpers.simpleGetWithBodyResponse();

    String expectedOutput =
            "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world";
    String actualOutput = ResponseFormatter.toString(testResponse);

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void correctlyFormatsResponseWithStartLineAndHeaders() {
    Response testResponse = TestHelpers.simpleGetResponse();

    String expectedOutput = "HTTP/1.1 200 OK\r\n";
    String actualOutput = ResponseFormatter.toString(testResponse);

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void correctlyFormatsResponseWithStartLineOnly() {
    Response testResponse = TestHelpers.notFoundResponse();

    String expectedOutput = "HTTP/1.1 404 Not Found\r\n";
    String actualOutput = ResponseFormatter.toString(testResponse);

    assertEquals(expectedOutput, actualOutput);
  }
}
