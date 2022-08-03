package server.constants;

public enum Path {
  ECHO_BODY("/echo_body"),
  HEAD_REQUEST("/head_request"),
  SIMPLE_GET("/simple_get"),
  SIMPLE_GET_WITH_BODY("/simple_get_with_body");

  private final String url;

  Path(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return url;
  }

  public static Path get(String stringPath) {
    for (Path path : values()) {
      if (path.url.equals(stringPath)) {
        return path;
      }
    }
    return null;
  }
}
