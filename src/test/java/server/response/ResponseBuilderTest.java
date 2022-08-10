package server.response;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class ResponseBuilderTest {
  private final String startLine = "HTTP/1.1 200 OK";
  private final List<String> headers = List.of("Content-Length: 11");
  private final String body = "Hello world";

  @Test
  public void setsAndBuildsResponseWithStartLineHeadersAndBody() {
    ResponseBuilder builder = new ResponseBuilder();
    builder.setStartLine(startLine);
    builder.addContentLengthHeader(body);
    builder.setBody(body);

    Response expectedResponse = new Response(startLine, headers, body);
    Response actualResponse = builder.build();

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void setsAndBuildsResponseWithStartLineAndHeaders() {
    ResponseBuilder builder = new ResponseBuilder();
    builder.setStartLine(startLine);
    builder.addContentLengthHeader(body);

    Response expectedResponse = new Response(startLine, headers, null);
    Response actualResponse = builder.build();

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void setsAndBuildsResponseWithStartLineOnly() {
    ResponseBuilder builder = new ResponseBuilder();
    builder.setStartLine(startLine);

    Response expectedResponse = new Response(startLine, null, null);
    Response actualResponse = builder.build();

    assertEquals(expectedResponse, actualResponse);
  }
}
