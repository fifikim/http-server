package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIo {
  private final BufferedReader input;
  private final OutputStream output;
  private final Socket clientSocket;

  public SocketIo(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    input = createSocketInput();
    output = createSocketOutput();
  }

  public String readLine() throws IOException {
    return input.readLine();
  }

  public String readBytes(int length) throws IOException {
    char[] container = new char[length];
    input.read(container, 0, length);
    return new String(container, 0, length);
  }

  public void sendBytes(byte[] bytes) throws IOException {
    output.write(bytes);
    output.flush();
  }

  public void closeStreams() throws IOException {
    input.close();
    output.close();
  }

  private BufferedReader createSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  private OutputStream createSocketOutput() throws IOException {
    return clientSocket.getOutputStream();
  }
}
