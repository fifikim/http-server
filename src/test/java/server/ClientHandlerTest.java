package server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import server.request.Request;
import server.response.Response;
import server.router.RequestRouter;

public class ClientHandlerTest {
  private final String testRequest = TestHelpers.stringRequest();
  private final Request request = TestHelpers.simpleGetRequest();
  private final Response testResponse = TestHelpers.simpleGetResponse();
  private OutputStream outputStream;
  private SocketIo socketIo;
  private Socket clientSocket;
  private RequestRouter router;

  @Before
  public void initialize() throws IOException {
    InputStream inputStream = new ByteArrayInputStream(testRequest.getBytes());
    outputStream = new ByteArrayOutputStream();

    ServerSocket serverSocket = mock(ServerSocket.class);
    clientSocket = TestHelpers.socket(inputStream, outputStream);
    when(serverSocket.accept()).thenReturn(clientSocket);

    router = mock(RequestRouter.class);
    when(router.getResponse(request)).thenReturn(testResponse);
  }

  public void runTestWithSocketIo() throws IOException {
    socketIo = new SocketIo(clientSocket);

    ServerSocketInterface socketInterface = new ServerSocketWrapper(clientSocket, socketIo);
    ClientHandler clientHandler = new ClientHandler(socketInterface, router);

    clientHandler.run();
  }

  public void runTestWithMockIo() throws IOException {
    socketIo = mock(SocketIo.class);
    when(socketIo.readLine()).thenReturn(testRequest, "");

    ServerSocketInterface socketInterface = new ServerSocketWrapper(clientSocket, socketIo);
    ClientHandler clientHandler = new ClientHandler(socketInterface, router);

    clientHandler.run();
  }

  @Test
  public void receivesRequest() throws IOException {
    runTestWithMockIo();
    verify(socketIo, times(2)).readLine();
  }

  @Test
  public void routesRequest() throws IOException {
    runTestWithSocketIo();
    verify(router).getResponse(request);
  }

  @Test
  public void sendsResponseIfRequestReceived() throws IOException {
    runTestWithSocketIo();

    String expectedSent = "HTTP/1.1 200 OK\r\n\r\n";
    String actualSent = outputStream.toString();

    assertEquals(expectedSent, actualSent);
  }

  @Test
  public void closesServerConnection() throws IOException {
    runTestWithSocketIo();
    verify(clientSocket).close();
  }
}
