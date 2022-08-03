package server;

import java.io.IOException;
import server.request.Request;
import server.response.Response;

public interface ServerSocketInterface {
  Request getRequest() throws IOException;

  void sendResponse(Response response) throws IOException;

  void closeSocket() throws IOException;
}
