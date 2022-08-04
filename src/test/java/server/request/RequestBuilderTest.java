package server.request;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import org.junit.Test;
import server.TestHelpers;
import server.constants.Method;
import server.constants.Path;

public class RequestBuilderTest {
  private final HashMap<String, String> headers = TestHelpers.mappedHeaders();

  @Test
  public void setsAndBuildsRequestWithMethodAndPathOnly() {
    Request expectedRequest = new Request(Method.GET, Path.SIMPLE_GET, null, null);
    Request actualRequest =
            new RequestBuilder()
                    .setMethod(Method.GET)
                    .setPath(Path.SIMPLE_GET)
                    .setHeaders(null)
                    .setBody(null)
                    .build();

    assertEquals(expectedRequest, actualRequest);
  }

  @Test
  public void setsAndBuildsRequestWithStartLineAndHeaders() {
    Request expectedRequest = new Request(Method.GET, Path.SIMPLE_GET, headers, null);
    Request actualRequest =
            new RequestBuilder()
                    .setMethod(Method.GET)
                    .setPath(Path.SIMPLE_GET)
                    .setHeaders(headers)
                    .setBody(null)
                    .build();

    assertEquals(expectedRequest, actualRequest);
  }

  @Test
  public void setsAndBuildsRequestWithStartLineHeadersAndBody() {
    String body = "Hello world";

    Request expectedRequest = new Request(Method.POST, Path.ECHO_BODY, headers, body);
    Request actualRequest =
            new RequestBuilder()
                    .setMethod(Method.POST)
                    .setPath(Path.ECHO_BODY)
                    .setHeaders(headers)
                    .setBody(body)
                    .build();

    assertEquals(expectedRequest, actualRequest);
  }
}
