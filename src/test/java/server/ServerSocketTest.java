package server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;
import server.response.Response;

public class ServerSocketTest {
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private SocketIo socketIo;
  private ServerSocketInterface serverSocketInterface;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  public void initialize() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = new ByteArrayOutputStream();

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream);
    when(serverSocket.accept()).thenReturn(clientSocket);

    socketIo = new SocketIo(clientSocket);
    serverSocketInterface = new ServerSocketWrapper(clientSocket, socketIo);
  }

  public void initializeWithInput(String testRequest) throws IOException {
    inputStream = new ByteArrayInputStream(testRequest.getBytes());
    outputStream = new ByteArrayOutputStream();

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream);
    when(serverSocket.accept()).thenReturn(clientSocket);

    socketIo = new SocketIo(clientSocket);
    serverSocketInterface = new ServerSocketWrapper(clientSocket, socketIo);
  }

  @Test
  public void readRequestReceivesRequest() throws IOException {
    String testRequest = "GET /simple_get HTTP/1.1\r\n\r\n";
    initializeWithInput(testRequest);

    String actualReceived = serverSocketInterface.getRequest();

    assertEquals(testRequest, actualReceived);
  }

  @Test
  public void readRequestReturnsNullForEmptyMessage() throws IOException {
    String testRequest = "  ";
    initializeWithInput(testRequest);

    String actualReceived = serverSocketInterface.getRequest();

    assertEquals(null, actualReceived);
  }

  @Test
  public void sendResponseSendsResponseWithoutBody() throws IOException {
    initialize();

    Response testResponse = TestHelpers.simpleGetResponse();
    serverSocketInterface.sendResponse(testResponse);

    String expectedOutput = testResponse.format();
    String actualOutput = outputStream.toString();

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void sendResponseSendsResponseWithBody() throws IOException {
    initialize();

    Response testResponse = TestHelpers.simpleGetWithBodyResponse();
    serverSocketInterface.sendResponse(testResponse);

    String expectedOutput = testResponse.format();
    String actualOutput = outputStream.toString();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    initialize();

    serverSocketInterface.closeSocket();

    verify(clientSocket).close();
  }
}
