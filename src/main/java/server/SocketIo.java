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

  public String read() throws IOException {
    StringBuilder request = new StringBuilder();

    while (input.available() > 0) {
      request.append((char) input.read());
    }

    return request.toString();
  }

  public void send(String message) throws IOException {
    System.out.println(message);
    output.write(message.getBytes("ASCII"));
    output.flush();
  }
}
