package server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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

  public static Request parsedRequest() {
    return new Request("GET", "/simple_get", "HTTP/1.1", "");
  }

  public static String stringRequestWithBody() {
    StringBuilder request = new StringBuilder();
    request.append("POST /echo_body HTTP/1.1\r\n");
    request.append("Content-Length: 11\r\n");
    request.append("\r\n");
    request.append("Hello world");

    return request.toString();
  }

  public static Response simpleGetResponse() {
    String startLine = "HTTP/1.1 200 OK";
    ArrayList<String> headers = new ArrayList<>(
            Arrays.asList("Allow: GET"));

    return new Response(startLine, headers, null);
  }

  public static Response simpleGetWithBodyResponse() {
    String startLine = "HTTP/1.1 200 OK";
    ArrayList<String> headers = new ArrayList<>(
            Arrays.asList("Allow: GET",
                          "Content-Length: 11"));
    String body = "Hello world";

    return new Response(startLine, headers, body);
  }

  public static Response notFoundResponse() {
    String startLine = "HTTP/1.1 404 Not Found";

    return new Response(startLine, null, null);
  }

  public static Response notAllowedResponse() {
    String startLine = "HTTP/1.1 405 Method Not Allowed";
    ArrayList<String> headers = new ArrayList<>(
            Arrays.asList("Allow: HEAD, OPTIONS"));

    return new Response(startLine, headers, null);
  }

  public static ArrayList<String> headersList() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add("Allow: GET, HEAD");
    headers.add("Content-Length: 11");

    return headers;
  }
}
