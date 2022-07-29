package server.constants;

public enum Protocol {
  DEFAULT("HTTP/1.1 ");

  private final String value;

  Protocol(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
