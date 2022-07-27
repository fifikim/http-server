//package httpserver;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import httpserver.server.ClientHandler;
//import httpserver.server.ServerSocketInterface;
//import httpserver.server.ServerSocketWrapper;
//import httpserver.server.SocketIo;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.net.InetAddress;
//import java.net.Socket;
//
//public class TestHelpers {
//  public static void restoreInitialStreams() {
//    System.setOut(System.out);
//    System.setIn(System.in);
//  }
//
//  public static ByteArrayOutputStream setConsoleOutput() {
//    ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
//    System.setOut(new PrintStream(mockOut));
//
//    return mockOut;
//  }
//
//  public static SocketIo socketIo(String message) throws IOException {
//    SocketIo socketIo = mock(SocketIo.class);
//    when(socketIo.readLine()).thenReturn(message);
//    when(socketIo.send(message)).thenReturn(message);
//
//    return socketIo;
//  }
//
//  public static Socket socket(InputStream in, OutputStream out, int port) throws IOException {
//    Socket clientSocket = mock(Socket.class);
//    when(clientSocket.getPort()).thenReturn(port);
//    when(clientSocket.getInetAddress()).thenReturn(InetAddress.getByName("localhost"));
//    when(clientSocket.getInputStream()).thenReturn(in);
//    when(clientSocket.getOutputStream()).thenReturn(out);
//
//    return clientSocket;
//  }
//
//  public static void initializeClient(Socket clientSocket, SocketIo socketIo) throws IOException {
//    //ClientSocketInterface socketInterface = new ClientSocketWrapper(clientSocket, socketIo);
//    //EchoClient echoClient = new EchoClient(socketInterface);
//    //echoClient.start();
//  }
//
//  public static void initializeServer(Socket clientSocket) throws IOException {
//    ServerSocketInterface serverSocketInterface = new ServerSocketWrapper(clientSocket);
//    ClientHandler clientHandler = new ClientHandler(serverSocketInterface);
//    clientHandler.run();
//  }
//
//  public static String getRequestHead() {
//    StringBuilder head = new StringBuilder();
//    head.append("GET /simple_get HTTP/1.1\r\n");
//    head.append("User-Agent: PostmanRuntime/7.29.2\r\n");
//    head.append("Accept: */*\r\n");
//    head.append("Postman-Token: 8137a6c3-68fc-45e8-9d4c-d235619a034e\r\n");
//    head.append("Host: localhost:5000\r\n");
//    head.append("Accept-Encoding: gzip, deflate, br\r\n");
//    head.append("Connection: keep-alive\r\n");
//
//    return head.toString();
//  }
//
//  public static String requestBody() {
//    return "Hello world";
//  }
//
//}
