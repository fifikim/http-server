package server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import server.response.Response;

public class TestHelpers {
  public static void restoreInitialStreams() {
    System.setOut(System.out);
    System.setIn(System.in);
  }

  public static ByteArrayOutputStream setConsoleOutput() {
    ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(mockOut));

    return mockOut;
  }

  public static SocketIo socketIo(String message) throws IOException {
    SocketIo socketIo = mock(SocketIo.class);
    when(socketIo.read()).thenReturn(message);
    //when(socketIo.sendResponse(message)).thenReturn(message);

    return socketIo;
  }

  public static Socket socket(InputStream in, OutputStream out, int port) throws IOException {
    Socket clientSocket = mock(Socket.class);
    when(clientSocket.getPort()).thenReturn(port);
    when(clientSocket.getInetAddress()).thenReturn(InetAddress.getByName("localhost"));
    when(clientSocket.getInputStream()).thenReturn(in);
    when(clientSocket.getOutputStream()).thenReturn(out);

    return clientSocket;
  }

  public static void initializeClient(Socket clientSocket, SocketIo socketIo) throws IOException {
    //ClientSocketInterface socketInterface = new ClientSocketWrapper(clientSocket, socketIo);
    //EchoClient echoClient = new EchoClient(socketInterface);
    //echoClient.start();
  }

  public static void initializeServer(Socket clientSocket) throws IOException {
    ServerSocketInterface serverSocketInterface = new ServerSocketWrapper(clientSocket);
    ClientHandler clientHandler = new ClientHandler(serverSocketInterface);
    clientHandler.run();
  }

  public static String stringRequest() {
    return "GET /simple_get HTTP/1.1\r\n";
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
    String startLine = "HTTP/1.1 200 OK\r\n";
    String headers = "Allow: GET\r\n";
    String body = "\r\n";

    return new Response(startLine, headers, body);
  }

  public static Response simpleGetWithBodyResponse() {
    String startLine = "HTTP/1.1 200 OK\r\n";
    String headers = "Allow: GET\r\n";
    String body = "\r\nHello world";

    return new Response(startLine, headers, body);
  }

  public static Response notFoundResponse() {
    String startLine = "HTTP/1.1 404 Not Found\r\n";
    String headers = "";
    String body = "\r\n";

    return new Response(startLine, headers, body);
  }

  public static Response notAllowedResponse() {
    String startLine = "HTTP/1.1 405 Method Not Allowed\r\n";
    String headers = "Allow: HEAD, OPTIONS\r\n";
    String body = "\r\n";

    return new Response(startLine, headers, body);
  }

  public static ArrayList<String> headersList() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add("Allow: GET, HEAD");
    headers.add("Content-Length: 11");

    return headers;
  }

  public static String headersString() {
    StringBuilder headers = new StringBuilder();
    headers.append("Allow: GET, HEAD\r\n");
    headers.append("Content-Length: 11\r\n");

    return headers.toString();
  }
}
