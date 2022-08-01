package server;

import java.io.IOException;
import java.net.Socket;
import server.response.Response;
import server.response.ResponseFormatter;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ServerSocketWrapper(Socket clientSocket, SocketIo socketIo) throws IOException {
    this.clientSocket = clientSocket;
    this.socketIo = socketIo;
  }

  public String getRequest() throws IOException {
    String request = socketIo.read();
    return (request.isBlank()) ? null : request;
  }

  public void sendResponse(Response response) throws IOException {
    String responseString = ResponseFormatter.toString(response);
    socketIo.send(responseString);
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
  }
}
