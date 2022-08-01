package server;

import java.io.IOException;
import server.request.Request;
import server.request.RequestParser;
import server.response.Response;
import server.router.RequestRouter;

public class ClientHandler implements Runnable {
  private final ServerSocketInterface socketInterface;
  private final RequestParser requestParser;
  private final RequestRouter requestRouter;

  public ClientHandler(
          ServerSocketInterface socketInterface,
          RequestParser requestParser,
          RequestRouter requestRouter) {
    this.socketInterface = socketInterface;
    this.requestParser = requestParser;
    this.requestRouter = requestRouter;
  }

  @Override
  public void run() {
    try {
      String rawRequest;

      while ((rawRequest = socketInterface.getRequest()) != null) {
        Request request = requestParser.parse(rawRequest);
        Response response = requestRouter.getResponse(request);
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
