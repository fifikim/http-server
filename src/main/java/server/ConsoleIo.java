package server;

import java.io.IOException;

public class ConsoleIo {
  public static void print(String output) {
    System.out.println(output);
  }

  public static void err(String message, IOException e) {
    System.out.println(message);
    System.out.println(e);
    System.exit(1);
  }
}
