package server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Test;

public class SocketIoTest {
  private final String testMessage = "test message";
  private SocketIo socketIo;
  private ByteArrayOutputStream outputStream;
  private ByteArrayInputStream inputStream;

  public void initialize() throws IOException {
    inputStream = new ByteArrayInputStream(testMessage.getBytes());
    outputStream = new ByteArrayOutputStream();
    Socket clientSocket = TestHelpers.socket(inputStream, outputStream);
    socketIo = new SocketIo(clientSocket);
  }

  public void initializeWithMockStreams() throws IOException {
    inputStream = mock(ByteArrayInputStream.class);
    outputStream = mock(ByteArrayOutputStream.class);

    Socket clientSocket = TestHelpers.socket(inputStream, outputStream);
    socketIo = new SocketIo(clientSocket);
  }

  @Test
  public void readsSingleLineOfInputStream() throws IOException {
    initialize();
    String actualReceived = socketIo.readLine();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void readsGivenNumberOfBytesOfInputStream() throws IOException {
    initialize();
    String actualReceived = socketIo.readBytes(12);

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void sendsSocketStreamOutput() throws IOException {
    initialize();
    socketIo.sendBytes(testMessage.getBytes());

    String actualSent = outputStream.toString();

    assertEquals(testMessage, actualSent);
  }

  @Test
  public void closesStreams() throws IOException {
    initializeWithMockStreams();
    socketIo.closeStreams();

    verify(inputStream).close();
    verify(outputStream).close();
  }
}
