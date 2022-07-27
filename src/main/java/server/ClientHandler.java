package server;

import java.io.IOException;
import server.request.Request;
import server.request.RequestParser;
import server.response.Response;
import server.router.RequestRouter;

public class ClientHandler implements Runnable {
  private final ServerSocketInterface socketInterface;

  public ClientHandler(ServerSocketInterface socketInterface) {
    this.socketInterface = socketInterface;
  }

  @Override
  public void run() {
    try {
      String rawRequest;

      while ((rawRequest = socketInterface.getRequest()) != null) {
        Request request = new RequestParser(rawRequest).parse();
        Response response = new RequestRouter(request).getResponse();
        socketInterface.sendResponse(response);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        socketInterface.closeSocket();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
