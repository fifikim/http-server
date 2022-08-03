package server.constants;

public enum Header {
  ACCEPT("Accept"),
  ACCEPT_ENCODING("Accept-Encoding"),
  ALLOW("Allow"),
  CONNECTION("Connection"),
  CONTENT_LENGTH("Content-Length"),
  CONTENT_TYPE("Content-Type"),
  HOST("Host"),
  LOCATION("Location"),
  POSTMAN_TOKEN("Postman-Token"),
  USER_AGENT("User-Agent");

  private final String value;

  Header(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public String toKey() {
    StringBuilder headerKey = new StringBuilder();
    headerKey.append(value);
    headerKey.append(": ");
    return headerKey.toString();
  }

  public static Header get(String stringHeader) {
    for (Header header : values()) {
      if (header.value.equals(stringHeader)) {
        return header;
      }
    }
    return null;
  }
}
