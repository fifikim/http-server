package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {
  public static byte[] readBytes(String fileName) throws IOException {
    String filePath = new File("").getAbsolutePath().concat("/web").concat(fileName);
    return Files.readAllBytes(new File(filePath).toPath());
  }
}


