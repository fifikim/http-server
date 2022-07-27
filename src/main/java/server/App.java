package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
  static ServerSocket serverSocket;

  public static void main(String[] args) throws IOException {
    int port = (args.length == 1) ? Integer.parseInt(args[0]) : 5000;

    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      ConsoleIo.print("Server now listening on port " + port + ".");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(clientSocket);
        ClientHandler clientHandler = new ClientHandler(serverSocketWrapper);

        new Thread(clientHandler).start();
      }
    } catch (IOException e) {
      ConsoleIo.err("Unable to open server socket", e);
    } finally {
      serverSocket.close();
    }
  }
}
