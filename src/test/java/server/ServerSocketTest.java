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
  private ServerSocketInterface serverSocketInterface;
  private ByteArrayInputStream inputStream;
  private ByteArrayOutputStream outputStream;

  public void initialize() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = new ByteArrayOutputStream();

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
    when(serverSocket.accept()).thenReturn(clientSocket);

    serverSocketInterface = new ServerSocketWrapper(clientSocket);
  }

  public void initializeWithInput(String testRequest) throws IOException {
    inputStream = new ByteArrayInputStream(testRequest.getBytes());
    outputStream = new ByteArrayOutputStream();

    serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
    when(serverSocket.accept()).thenReturn(clientSocket);

    serverSocketInterface = new ServerSocketWrapper(clientSocket);
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
    Response testResponse = TestHelpers.simpleGetResponse();
    initialize();

    serverSocketInterface.sendResponse(testResponse);

    assertEquals(testResponse.toString(), outputStream.toString());
  }

  @Test
  public void sendResponseSendsResponseWithBody() throws IOException {
    Response testResponse = TestHelpers.simpleGetWithBodyResponse();
    initialize();

    serverSocketInterface.sendResponse(testResponse);

    assertEquals(testResponse.toString(), outputStream.toString());
  }

  @Test
  public void closesConnectionAndStreams() throws IOException {
    initialize();
    serverSocketInterface.closeSocket();

    verify(clientSocket).close();
  }
}
