//package server;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import org.junit.Test;
//
//public class ServerSocketTest {
//  private ServerSocket serverSocket;
//  private Socket clientSocket;
//  private SocketIo socketIo;
//  private ServerSocketInterface serverSocketInterface;
//  private ByteArrayInputStream inputStream;
//  private ByteArrayOutputStream outputStream;
//  private String[] mockRequests;
//
//  public void initialize(String testRequest) throws IOException {
//    inputStream = new ByteArrayInputStream(testRequest.getBytes());
//    outputStream = new ByteArrayOutputStream();
//
//    serverSocket = mock(ServerSocket.class);
//    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
//    when(serverSocket.accept()).thenReturn(clientSocket);
//
//    socketIo = TestHelpers.socketIo(testRequest);
//    serverSocketInterface = new ServerSocketWrapper(clientSocket);
//  }
//
//  public void initializeWithMockStreams() throws IOException {
//    inputStream = mock(ByteArrayInputStream.class);
//    outputStream = mock(ByteArrayOutputStream.class);
//
//    serverSocket = mock(ServerSocket.class);
//    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
//    when(serverSocket.accept()).thenReturn(clientSocket);
//
//    socketIo = mock(SocketIo.class);
//    serverSocketInterface = new ServerSocketWrapper(clientSocket);
//  }
//
//  @Test
//  public void getRequestHeadReceivesStartLineAndHeaders() throws IOException {
//    String testRequest = TestHelpers.getRequestHead();
//    initialize(testRequest);
//    String actualReceived = serverSocketInterface.getRequest();
//
//    assertEquals(testRequest, actualReceived);
//  }
//
//  @Test
//  public void getRequestBodyReceivesBytesGiven() throws IOException {
//    String testRequest = TestHelpers.requestBody();
//    int byteLength = testRequest.getBytes().length;
//    initialize(testRequest);
//    //String actualReceived = serverSocketInterface.getRequestBody(byteLength);
//
//    //assertEquals(testRequest, actualReceived);
//  }
//
//  @Test
//  public void closesConnectionAndStreams() throws IOException {
//    initializeWithMockStreams();
//    serverSocketInterface.closeSocket();
//
//    verify(clientSocket).close();
//    verify(inputStream).close();
//    verify(outputStream).close();
//  }
//}
