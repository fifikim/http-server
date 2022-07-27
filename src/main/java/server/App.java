package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.request.RequestParser;
import server.router.RequestRouter;

public class App {
  static ServerSocket serverSocket;

  public static void main(String[] args) throws IOException {
    try {
      serverSocket = new ServerSocket(5000);
      serverSocket.setReuseAddress(true);
      ConsoleIo.print("Server now listening on port 5000.");

      while (true) {
        Socket clientSocket = serverSocket.accept();

        SocketIo socketIo = new SocketIo(clientSocket);
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(clientSocket, socketIo);
        RequestParser parser = new RequestParser();
        RequestRouter router = new RequestRouter();
        ClientHandler clientHandler = new ClientHandler(serverSocketWrapper, parser, router);

        new Thread(clientHandler).start();
      }
    } catch (IOException e) {
      ConsoleIo.err("Unable to open server socket", e);
    } finally {
      serverSocket.close();
    }
  }
}
