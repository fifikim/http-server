package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {
  public static String read(String fileName) throws IOException {
    String filePath = new File("").getAbsolutePath().concat("/web").concat(fileName);

    StringBuilder bodyBytes = new StringBuilder();
    BufferedReader input = new BufferedReader(new java.io.FileReader(filePath));
    String pageContent;

    while ((pageContent = input.readLine()) != null) {
      bodyBytes.append(pageContent);
    }

    input.close();
    return bodyBytes.toString();
  }
}
