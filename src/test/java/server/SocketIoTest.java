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
  public void readsSocketStreamInput() throws IOException {
    initialize();
    String actualReceived = socketIo.read();

    assertEquals(testMessage, actualReceived);
  }

  @Test
  public void sendsSocketStreamOutput() throws IOException {
    initialize();
    socketIo.send(testMessage);

    assertEquals(testMessage, outputStream.toString());
  }
}
