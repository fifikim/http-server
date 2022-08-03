package server.router;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;
import server.constants.ContentType;
import server.constants.Method;
import server.constants.Path;

public class HeadersBuilderTest {
  @Test
  public void setsAndBuildsHeadersWithCorrectAllowHeader() {
    HeadersBuilder headers = new HeadersBuilder();
    headers.addAllow(List.of(Method.GET, Method.HEAD));

    List<String> expectedHeaders = List.of("Allow: GET, HEAD");
    List<String> actualHeaders = headers.build();

    assertEquals(expectedHeaders, actualHeaders);
  }

  @Test
  public void setsCorrectContentTypeHeader() {
    HeadersBuilder headers = new HeadersBuilder();
    headers.addContentType(ContentType.XML);

    List<String> expectedHeaders = List.of(
            "Content-Type: application/xml;charset=utf-8");
    List<String> actualHeaders = headers.build();

    assertEquals(expectedHeaders, actualHeaders);
  }

  @Test
  public void setsCorrectContentLengthHeader() {
    HeadersBuilder headers = new HeadersBuilder();
    headers.addContentLength("Hello world");

    List<String> expectedHeaders = List.of("Content-Length: 11");
    List<String> actualHeaders = headers.build();

    assertEquals(expectedHeaders, actualHeaders);
  }

  @Test
  public void setsCorrectLocationHeader() {
    HeadersBuilder headers = new HeadersBuilder();
    headers.addLocation("0.0.0.0:5000", Path.SIMPLE_GET);

    List<String> expectedHeaders = List.of(
            "Location: http://0.0.0.0:5000/simple_get");
    List<String> actualHeaders = headers.build();

    assertEquals(expectedHeaders, actualHeaders);
  }

  @Test
  public void setsAndBuildsHeadersWithMultipleHeaderTypes() {
    HeadersBuilder headers = new HeadersBuilder();
    headers.addAllow(List.of(Method.GET, Method.HEAD));
    headers.addContentType(ContentType.XML);
    headers.addContentLength("Hello world");
    headers.addLocation("0.0.0.0:5000", Path.SIMPLE_GET);

    List<String> expectedHeaders = List.of("Allow: GET, HEAD",
            "Content-Type: application/xml;charset=utf-8",
            "Content-Length: 11",
            "Location: http://0.0.0.0:5000/simple_get");
    List<String> actualHeaders = headers.build();

    assertEquals(expectedHeaders, actualHeaders);
  }
}
