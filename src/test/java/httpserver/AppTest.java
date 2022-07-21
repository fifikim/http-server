package httpserver;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
  private final ByteArrayOutputStream mockOut = new ByteArrayOutputStream();

  @Before
  public void setStreamOut() {
    System.setOut(new PrintStream(mockOut));
  }

  @After
  public void restoreInitialStreams() {
    System.setOut(System.out);
  }

  @Test public void appPrintsHelloWorldGreeting() throws IOException {
    App.main(null);
    String output = mockOut.toString().strip();

    assertEquals("Hello World!", output);
  }
}
