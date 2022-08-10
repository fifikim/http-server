package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIo {
  private final BufferedReader input;
  private final PrintWriter output;
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

  public void send(String message) {
    output.println(message);
  }

  public void closeStreams() throws IOException {
    input.close();
    output.close();
  }

  private BufferedReader createSocketInput() throws IOException {
    return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  private PrintWriter createSocketOutput() throws IOException {
    return new PrintWriter(clientSocket.getOutputStream(), true);
  }
}
