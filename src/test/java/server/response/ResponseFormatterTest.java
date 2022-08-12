package server.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import server.TestHelpers;

public class ResponseFormatterTest {
  @Test
  public void correctlyFormatsResponseWithStartLineHeadersAndBody() throws IOException {
    Response testResponse = TestHelpers.simpleGetWithBodyResponse();

    byte[] expectedOutput =
            "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world".getBytes();
    byte[] actualOutput = ResponseFormatter.toBytes(testResponse);

    assertEquals(Arrays.toString(expectedOutput), Arrays.toString(actualOutput));
  }

  @Test
  public void correctlyFormatsResponseWithStartLineAndHeaders() throws IOException {
    Response testResponse = TestHelpers.simpleGetResponse();

    byte[] expectedOutput = "HTTP/1.1 200 OK\r\n\r\n".getBytes();
    byte[] actualOutput = ResponseFormatter.toBytes(testResponse);

    assertEquals(Arrays.toString(expectedOutput), Arrays.toString(actualOutput));
  }

  @Test
  public void correctlyFormatsResponseWithStartLineOnly() throws IOException {
    Response testResponse = TestHelpers.notFoundResponse();

    byte[] expectedOutput = "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
    byte[] actualOutput = ResponseFormatter.toBytes(testResponse);

    assertEquals(Arrays.toString(expectedOutput), Arrays.toString(actualOutput));
  }
}
