package server.router;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import server.TestHelpers;

public class FileReaderTest {
  @Test
  public void readsContentsOfHtmlFile() throws IOException {
    String expectedContents = TestHelpers.htmlFileContents();
    String fileContents = FileReader.read("/health-check.html");

    assertEquals(expectedContents, fileContents);
  }
}
