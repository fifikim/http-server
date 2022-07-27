package server.constants;

public enum Status {
  OK("200 OK"),
  NOT_FOUND("404 Not Found"),
  NOT_ALLOWED("405 Method Not Allowed");

  private final String getValue;

  Status(String value) {
    getValue = value;
  }

  @Override
  public String toString() {
    return getValue;
  }
}
