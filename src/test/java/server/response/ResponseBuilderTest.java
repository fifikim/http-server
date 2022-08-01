package server.response;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class ResponseBuilderTest {
  private final String startLine = "HTTP/1.1 200 OK";
  private final List<String> headers = List.of("Allow: GET, HEAD",
                                                      "Content-Length: 11");

  @Test
  public void setsAndBuildsResponseWithStartLineHeadersAndBody() {
    String body = "Hello world";

    Response expectedResponse = new Response(startLine, headers, body);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(startLine)
                    .setHeaders(headers)
                    .setBody(body)
                    .build();

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void setsAndBuildsResponseWithStartLineAndHeaders() {
    Response expectedResponse = new Response(startLine, headers, null);
    Response actualResponse =
            new ResponseBuilder()
                    .setStartLine(startLine)
                    .setHeaders(headers)
                    .setBody(null)
                    .build();

    assertEquals(expectedResponse, actualResponse);
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

    assertEquals(expectedResponse, actualResponse);
  }
}
