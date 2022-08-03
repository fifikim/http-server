package server;

import static org.junit.Assert.assertEquals;

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
    socketIo.send(testMessage);

    String expectedSent = "test message\n";
    String actualSent = outputStream.toString();


    assertEquals(expectedSent, actualSent);
  }

  @Test
  public void closesStreams() {
    //
  }
}
