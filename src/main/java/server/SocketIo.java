package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIo {
  private final InputStream input;
  private final OutputStream output;

  public SocketIo(Socket clientSocket) throws IOException {
    input = clientSocket.getInputStream();
    output = clientSocket.getOutputStream();
  }

  public String getRequest() throws IOException {
    StringBuilder request = new StringBuilder();

    while (input.available() > 0) {
      request.append((char) input.read());
    }

    System.out.println(request.toString());
    return request.toString();
  }

  public void sendResponse(String message) throws IOException {
    System.out.println(message);
    output.write(message.getBytes("ASCII"));
    output.flush();
  }
}
