package server.response;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import server.TestHelpers;

public class ResponseBuilderTest {
  private final String startLine = "HTTP/1.1 200 OK\r\n";
  private final String protocol = "HTTP/1.1";
  private final String status = "200 OK";
  private final ArrayList<String> headersList = TestHelpers.headersList();
  private final String headersString = TestHelpers.headersString();

  @Test
  public void setsAndBuildsResponseWithStartLineHeadersAndBody() {
    String body = "\r\nHello world";

    Response expectedResponse = new Response(startLine, headersString, body);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(protocol, status)
                    .setHeaders(headersList)
                    .setBody("Hello world")
                    .build();

    assertEquals(expectedResponse.toString(), actualResponse.toString());
  }

  @Test
  public void setsAndBuildsResponseWithStartLineAndHeaders() {
    Response expectedResponse = new Response(startLine, headersString, "\r\n");
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(protocol, status)
                    .setHeaders(headersList)
                    .build();

    assertEquals(expectedResponse.toString(), actualResponse.toString());
  }

  @Test
  public void setsAndBuildsResponseWithStartLineOnly() {
    Response expectedResponse = new Response(startLine, "", "\r\n");
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(protocol, status)
                    .build();

    assertEquals(expectedResponse.toString(), actualResponse.toString());
  }
}
