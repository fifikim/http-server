package server;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleIoTest {
  private final ByteArrayOutputStream mockOut = new ByteArrayOutputStream();

  @Before
  public void setStreamOut() {
    System.setOut(new PrintStream(mockOut));
  }

  @After
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  public void printOutputsMessageToTerminal() {
    String message = "Test message";
    ConsoleIo.print(message);
    assertEquals(message, mockOut.toString().strip());
  }
}
