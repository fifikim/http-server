package server.response;

import java.io.IOException;
import server.SocketIo;

public class ResponseWriter {
  private final SocketIo socketIo;

  public ResponseWriter(SocketIo socketIo) {
    this.socketIo = socketIo;
  }

  public void writeResponse(Response response) throws IOException {
    if (response != null) {
      byte[] responseBytes = ResponseFormatter.toBytes(response);
      socketIo.sendBytes(responseBytes);
    }
  }
}
