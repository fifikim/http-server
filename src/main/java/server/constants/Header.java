package server.constants;

public enum Header {
  ALLOW("Allow"),
  CONTENT_LENGTH("Content-Length");

  private final String value;

  Header(String value) {
    this.value = value;
  }

  public String toKey() {
    StringBuilder headerKey = new StringBuilder();
    headerKey.append(value);
    headerKey.append(": ");
    return headerKey.toString();
  }

  @Override
  public String toString() {
    return value;
  }
}
