package server;

import java.io.IOException;
import server.response.Response;

public interface ServerSocketInterface {
  String getRequest() throws IOException;

  void sendResponse(Response response) throws IOException;

  void closeSocket() throws IOException;
}
