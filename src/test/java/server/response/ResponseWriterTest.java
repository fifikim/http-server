package server.response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Test;
import server.SocketIo;
import server.TestHelpers;

public class ResponseWriterTest {
  private final Response response = TestHelpers.simpleGetWithBodyResponse();
  private final SocketIo socketIo;
  ByteArrayOutputStream outputStream;

  public ResponseWriterTest() throws IOException {
    outputStream = new ByteArrayOutputStream();
    ByteArrayInputStream inputStream = mock(ByteArrayInputStream.class);
    Socket clientSocket = TestHelpers.socket(inputStream, outputStream);
    socketIo = new SocketIo(clientSocket);
  }

  @Test
  public void convertsResponseToBytesAndWritesToOutputStream() throws IOException {
    new ResponseWriter(socketIo).writeResponse(response);

    String expectedOutput = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world";
    String actualOutput = outputStream.toString();

    assertEquals(expectedOutput, actualOutput);
  }
}
