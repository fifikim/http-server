package server.response;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import server.TestHelpers;

public class ResponseBuilderTest {
  private final String startLine = "HTTP/1.1 200 OK";
  private final ArrayList<String> headersList = TestHelpers.headersList();

  @Test
  public void setsAndBuildsResponseWithStartLineHeadersAndBody() {
    String body = "Hello world";

    Response expectedResponse = new Response(startLine, headersList, body);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(startLine)
                    .setHeaders(headersList)
                    .setBody(body)
                    .build();

    assertEquals(expectedResponse.format(), actualResponse.format());
  }

  @Test
  public void setsAndBuildsResponseWithStartLineAndHeaders() {
    Response expectedResponse = new Response(startLine, headersList, null);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(startLine)
                    .setHeaders(headersList)
                    .setBody(null)
                    .build();

    assertEquals(expectedResponse.format(), actualResponse.format());
  }

  @Test
  public void setsAndBuildsResponseWithStartLineOnly() {
    Response expectedResponse = new Response(startLine, null, null);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(startLine)
                    .setHeaders(null)
                    .setBody(null)
                    .build();

    assertEquals(expectedResponse.format(), actualResponse.format());
  }
}
