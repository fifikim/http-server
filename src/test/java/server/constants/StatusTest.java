package server.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatusTest {
  @Test
  public void enumToStringReturnsCorrectStatusCode() {
    assertEquals("200 OK", Status.OK.toString());
    assertEquals("404 Not Found", Status.NOT_FOUND.toString());
    assertEquals("405 Method Not Allowed", Status.NOT_ALLOWED.toString());
  }
}
