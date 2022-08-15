package server;

import java.io.IOException;
import java.net.Socket;
import server.request.Request;
import server.request.RequestReader;
import server.response.Response;
import server.response.ResponseWriter;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ServerSocketWrapper(Socket clientSocket, SocketIo socketIo) throws IOException {
    this.clientSocket = clientSocket;
    this.socketIo = socketIo;
  }

  public Request getRequest() throws IOException {
    return new RequestReader(socketIo).readRequest();
  }

  public void sendResponse(Response response) throws IOException {
    new ResponseWriter(socketIo).writeResponse(response);
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    socketIo.closeStreams();
  }
}
