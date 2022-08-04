package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import server.constants.Format;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;
import server.request.RequestBuilder;
import server.request.RequestParser;
import server.response.Response;
import server.response.ResponseFormatter;

public class ServerSocketWrapper implements ServerSocketInterface {
  private final Socket clientSocket;
  private final SocketIo socketIo;

  public ServerSocketWrapper(Socket clientSocket, SocketIo socketIo) throws IOException {
    this.clientSocket = clientSocket;
    this.socketIo = socketIo;
  }

  public Request getRequest() throws IOException {
    String rawRequest = getRequestHead();

    if (!rawRequest.isBlank()) {
      RequestParser parser = new RequestParser(rawRequest);
      Method method = parser.method();
      Path path = parser.path();
      HashMap<Header, String> headers = parser.headers();

      String body = null;
      if (headers.containsKey(Header.CONTENT_LENGTH)) {
        int bytes = Integer.parseInt(headers.get(Header.CONTENT_LENGTH));
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

  public void sendResponse(Response response) {
    if (response != null) {
      String responseString = ResponseFormatter.toString(response);
      socketIo.send(responseString);
    }
  }

  public void closeSocket() throws IOException {
    clientSocket.close();
    socketIo.closeStreams();
  }
}
