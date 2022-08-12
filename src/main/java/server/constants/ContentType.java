package server.constants;

public enum ContentType {
  TEXT("text/plain;charset=utf-8"),
  HTML("text/html;charset=utf-8"),
  JSON("application/json;charset=utf-8"),
  XML("application/xml;charset=utf-8"),
  JPG("image/jpeg"),
  PNG("image/png"),
  GIF("image/gif");

  private final String value;

  ContentType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
