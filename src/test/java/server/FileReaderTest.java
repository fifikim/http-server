package server;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.Test;


public class FileReaderTest {
  @Test
  public void readsContentsOfHtmlFile() throws IOException {
    byte[] fileContents = FileReader.readBytes("/health-check.html");

    assertNotNull(fileContents);
  }

  @Test
  public void readsContentsOfJpgFile() throws IOException {
    byte[] fileContents = FileReader.readBytes("/kitteh.jpg");

    assertNotNull(fileContents);
  }

  @Test
  public void readsContentsOfPngFile() throws IOException {
    byte[] fileContents = FileReader.readBytes("/doggo.png");

    assertNotNull(fileContents);
  }

  @Test
  public void readsContentsOfGifFile() throws IOException {
    byte[] fileContents = FileReader.readBytes("/kisses.gif");

    assertNotNull(fileContents);
  }
}
