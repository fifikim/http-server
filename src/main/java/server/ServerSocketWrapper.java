package server;

import java.io.IOException;
import java.net.Socket;
import server.response.Response;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ServerSocketWrapper(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    socketIo = createSocketStreams();
  }

  public String getRequest() throws IOException {
    String request = socketIo.read();
    return (request.isBlank()) ? null : request;
  }

  public void sendResponse(Response response) throws IOException {
    socketIo.send(response.toString());
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
  }

  private SocketIo createSocketStreams() throws IOException {
    return new SocketIo(clientSocket);
  }
}
