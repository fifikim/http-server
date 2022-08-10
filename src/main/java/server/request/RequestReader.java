package server.request;

import java.io.IOException;
import java.util.HashMap;
import server.SocketIo;
import server.constants.Format;
import server.constants.Method;
import server.constants.Path;

public class RequestReader {
  private final SocketIo socketIo;

  public RequestReader(SocketIo socketIo) {
    this.socketIo = socketIo;
  }

  public Request readRequest() throws IOException {
    String rawRequest = getRequestHead();

    if (!rawRequest.isBlank()) {
      RequestParser parser = new RequestParser(rawRequest);
      Method method = parser.method();
      Path path = parser.path();
      HashMap<String, String> headers = parser.headers();

      String body = null;
      if (headers.containsKey("Content-Length")) {
        int bytes = Integer.parseInt(headers.get("Content-Length"));
        body = getRequestBody(bytes);
      }

      return new RequestBuilder()
              .setMethod(method)
              .setPath(path)
              .setHeaders(headers)
              .setBody(body)
              .build();
    }
    return null;
  }

  private String getRequestHead() throws IOException {
    StringBuilder request = new StringBuilder();
    String line;

    while ((line = socketIo.readLine()) != null) {
      request.append(line).append(Format.BREAK);

      if (line.isBlank()) {
        break;
      }
    }

    return request.toString();
  }

  private String getRequestBody(int length) throws IOException {
    return socketIo.readBytes(length);
  }
}
