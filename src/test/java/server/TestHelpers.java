package server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
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
    return new Request(Method.GET, Path.SIMPLE_GET, "");
  }

  public static String stringRequestWithBody() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 11\r\n");
    request.append("\r\n");
    request.append("Hello world");

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
    List<String> headers = List.of("Allow: GET");

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
}
