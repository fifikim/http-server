package server.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;
import server.SocketIo;
import server.TestHelpers;

public class RequestReaderTest {
  RequestReader reader;

  public void initialize(String testRequest) throws IOException {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(testRequest.getBytes());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    ServerSocket serverSocket = mock(ServerSocket.class);
    Socket clientSocket = TestHelpers.socket(inputStream, outputStream);
    when(serverSocket.accept()).thenReturn(clientSocket);

    SocketIo socketIo = new SocketIo(clientSocket);
    reader = new RequestReader(socketIo);
  }

  @Test
  public void readRequestReceivesRequestWithStartLineOnly() throws IOException {
    String testRequest = "GET /simple_get HTTP/1.1\r\n\r\n";
    initialize(testRequest);

    Request expectedRequest = TestHelpers.simpleGetRequest();
    Request actualReceived = reader.readRequest();

    assertEquals(expectedRequest, actualReceived);
  }

  @Test
  public void readRequestReceivesRequestWithStartLineAndHeader() throws IOException {
    String testRequest = TestHelpers.stringRequestWithHeader();
    initialize(testRequest);

    Request expectedRequest = TestHelpers.requestWithHeader();
    Request actualReceived = reader.readRequest();

    assertEquals(expectedRequest, actualReceived);
  }

  @Test
  public void readRequestReceivesRequestWithStartLineHeaderAndBody() throws IOException {
    String testRequest = TestHelpers.stringRequestWithHeaderAndBody();
    initialize(testRequest);

    Request expectedRequest = TestHelpers.requestWithHeaderAndBody();
    Request actualReceived = reader.readRequest();

    assertEquals(expectedRequest, actualReceived);
  }

  @Test
  public void readRequestReceivesRequestWithMultipleHeaders() throws IOException {
    String testRequest = TestHelpers.stringRequestWithMultipleHeaders();
    initialize(testRequest);

    Request expectedRequest = TestHelpers.requestWithMultipleHeaders();
    Request actualReceived = reader.readRequest();

    assertEquals(expectedRequest, actualReceived);
  }

  @Test
  public void readRequestReceivesRequestWithBodyWithLineBreaks() throws IOException {
    String testRequest = TestHelpers.stringRequestWithBodyWithBreaks();
    initialize(testRequest);

    Request expectedRequest = TestHelpers.requestWithBodyWithBreaks();
    Request actualReceived = reader.readRequest();

    assertEquals(expectedRequest, actualReceived);
  }

  @Test
  public void readRequestReturnsNullForEmptyMessage() throws IOException {
    String testRequest = "  ";
    initialize(testRequest);

    Request actualReceived = reader.readRequest();

    assertNull(actualReceived);
  }
}
