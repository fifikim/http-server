package server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;
import server.response.Response;

public class TestHelpers {
  public static Socket socket(InputStream in, OutputStream out) throws IOException {
    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getInputStream()).thenReturn(in);
    when(clientSocket.getOutputStream()).thenReturn(out);
    return clientSocket;
  }

  public static String stringRequest() {
    return "GET /simple_get HTTP/1.1\r\n";
  }

  public static String stringRequestWithBadRoute() {
    return "GET /bad_route HTTP/1.1\r\n";
  }

  public static String stringRequestWithBadMethod() {
    return "get /simple_get HTTP/1.1\r\n";
  }

  public static Request parsedRequest() {
    return new Request(Method.GET, Path.SIMPLE_GET, null, null);
  }

  public static String stringRequestWithHeader() {
    StringBuilder request = new StringBuilder();
    request.append("GET /simple_get HTTP/1.1\r\n");
    request.append("Accept: */*\r\n");
    request.append("\r\n");

    return request.toString();
  }

  public static String stringRequestWithBody() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 11\r\n");
    request.append("\r\n");
    request.append("Hello world");

    return request.toString();
  }

  public static String stringRequestWithMultipleHeaders() {
    StringBuilder request = new StringBuilder();
    request.append("GET /simple_get HTTP/1.1\r\n");
    request.append("Accept: */*\r\n");
    request.append("Host: localhost:5000\r\n");
    request.append("Accept-Encoding: gzip, deflate, br\r\n");
    request.append("Connection: keep-alive\r\n");

    return request.toString();
  }

  public static String stringRequestWithBodyWithBreaks() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 11\r\n");
    request.append("\r\n");
    request.append("Hello world\r\n");
    request.append("\r\n");
    request.append("Second line\r\n");
    request.append("\r\n");
    request.append("Third line");

    return request.toString();
  }

  public static Response simpleGetResponse() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Allow: GET, HEAD");

    return new Response(startLine, headers, null);
  }

  public static Response simpleGetWithBodyResponse() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Allow: GET",
                          "Content-Length: 11");
    String body = "Hello world";

    return new Response(startLine, headers, body);
  }

  public static Response notFoundResponse() {
    String startLine = "HTTP/1.1 404 Not Found";

    return new Response(startLine, null, null);
  }

  public static Response badRequestResponse() {
    String startLine = "HTTP/1.1 400 Bad Request";

    return new Response(startLine, null, null);
  }

  public static Response notAllowedResponse() {
    String startLine = "HTTP/1.1 405 Method Not Allowed";
    List<String> headers = List.of("Allow: HEAD, OPTIONS");

    return new Response(startLine, headers, null);
  }

  public static Response echoBodyResponse() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Allow: POST",
                    "Content-Length: 12");
    String body = "test message";

    return new Response(startLine, headers, body);
  }

  public static Response redirectResponse() {
    String startLine = "HTTP/1.1 301 Moved Permanently";
    List<String> headers = List.of("Allow: GET",
                    "Location: http://0.0.0.0:5000/simple_get");

    return new Response(startLine, headers, null);
  }

  public static Response methodOptionsResponse() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Allow: GET, HEAD, OPTIONS");

    return new Response(startLine, headers, null);
  }

  public static Response methodOptions2Response() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Allow: GET, HEAD, OPTIONS, POST, PUT");

    return new Response(startLine, headers, null);
  }

  public static HashMap<Header, String> mappedHeaders() {
    HashMap<Header, String> mappedHeaders = new HashMap<>();
    mappedHeaders.put(Header.USER_AGENT, "PostmanRuntime/7.29.2");
    mappedHeaders.put(Header.ACCEPT, "*/*");
    mappedHeaders.put(Header.HOST, "0.0.0.0:5000");
    mappedHeaders.put(Header.ACCEPT_ENCODING, "gzip, deflate, br");
    mappedHeaders.put(Header.CONNECTION, "keep-alive");

    return mappedHeaders;
  }
}
