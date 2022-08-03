package server.constants;

public enum Status {
  OK("200 OK"),
  BAD_REQUEST("400 Bad Request"),
  MOVED("301 Moved Permanently"),
  NOT_ALLOWED("405 Method Not Allowed"),
  NOT_FOUND("404 Not Found");

  private final String value;

  Status(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
