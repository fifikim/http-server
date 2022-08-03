package server;

import java.io.IOException;
import server.request.Request;
import server.response.Response;
import server.router.RequestRouter;

public class ClientHandler implements Runnable {
  private final ServerSocketInterface socketInterface;
  private final RequestRouter requestRouter;

  public ClientHandler(
          ServerSocketInterface socketInterface,
          RequestRouter requestRouter) {
    this.socketInterface = socketInterface;
    this.requestRouter = requestRouter;
  }

  @Override
  public void run() {
    try {
      Request request = socketInterface.getRequest();

      if (request != null) {
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
