package server.constants;

public enum Status {
  OK("200 OK"),
  BAD_REQUEST("400 Bad Request"),
  NOT_FOUND("404 Not Found"),
  NOT_ALLOWED("405 Method Not Allowed"),
  MOVED("301 Moved Permanently");

  private final String value;

  Status(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public String format() {
    StringBuilder startLine = new StringBuilder();
    startLine.append("HTTP/1.1 ");
    startLine.append(this.value);

    return startLine.toString();
  }
}
