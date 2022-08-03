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

  public static Request simpleGetRequest() {
    HashMap<Header, String> emptyHeaders = new HashMap<>();
    return new Request(Method.GET, Path.SIMPLE_GET, emptyHeaders, null);
  }

  public static String stringRequestWithHeader() {
    StringBuilder request = new StringBuilder();
    request.append("GET /simple_get HTTP/1.1\r\n");
    request.append("Accept: */*\r\n");
    request.append("\r\n");

    return request.toString();
  }

  public static Request requestWithHeader() {
    HashMap<Header, String> headers = new HashMap<>();
    headers.put(Header.ACCEPT, "*/*");
    return new Request(Method.GET, Path.SIMPLE_GET, headers, null);
  }

  public static String stringRequestWithHeaderAndBody() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 11\r\n");
    request.append("\r\n");
    request.append("Hello world");

    return request.toString();
  }

  public static Request requestWithHeaderAndBody() {
    HashMap<Header, String> headers = new HashMap<>();
    headers.put(Header.CONTENT_LENGTH, "11");
    return new Request(Method.POST, Path.ECHO_BODY, headers, "Hello world");
  }

  public static String stringRequestWithMultipleHeaders() {
    StringBuilder request = new StringBuilder();
    request.append("GET /simple_get HTTP/1.1\r\n");
    request.append("User-Agent: PostmanRuntime/7.29.2\r\n");
    request.append("Accept: */*\r\n");
    request.append("Host: 0.0.0.0:5000\r\n");
    request.append("Accept-Encoding: gzip, deflate, br\r\n");
    request.append("Connection: keep-alive\r\n");

    return request.toString();
  }

  public static Request requestWithMultipleHeaders() {
    HashMap<Header, String> headers = mappedHeaders();

    return new Request(Method.GET, Path.SIMPLE_GET, headers, null);
  }

  public static String stringRequestWithBodyWithBreaks() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 40\r\n");
    request.append("\r\n");
    request.append(bodyWithLineBreaks());

    return request.toString();
  }

  public static Request requestWithBodyWithBreaks() {
    HashMap<Header, String> headers = new HashMap<>();
    headers.put(Header.CONTENT_LENGTH, "40");
    String body = bodyWithLineBreaks();

    return new Request(Method.POST, Path.ECHO_BODY, headers, body);
  }

  public static Response simpleGetResponse() {
    String startLine = "HTTP/1.1 200 OK";

    return new Response(startLine, null, null);
  }

  public static Response simpleGetWithBodyResponse() {
    String startLine = "HTTP/1.1 200 OK";
    List<String> headers = List.of("Content-Length: 11");
    String body = "Hello world";

    return new Response(startLine, headers, body);
  }

  public static String stringGetWithBodyResponse() {
    StringBuilder response = new StringBuilder();
    response.append("HTTP/1.1 200 OK\r\n");
    response.append("Content-Length: 11\r\n\r\n");
    response.append("Hello world\n");

    return response.toString();
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
    List<String> headers = List.of("Content-Length: 12");
    String body = "test message";

    return new Response(startLine, headers, body);
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

  public static String bodyWithLineBreaks() {
    StringBuilder body = new StringBuilder();
    body.append("Hello world\r\n");
    body.append("\r\n");
    body.append("Second line\r\n");
    body.append("\r\n");
    body.append("Third line");

    return body.toString();
  }
}
