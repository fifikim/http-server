package server.constants;

public enum Method {
  CONNECT("CONNECT"),
  DELETE("DELETE"),
  GET("GET"),
  HEAD("HEAD"),
  OPTIONS("OPTIONS"),
  PATCH("PATCH"),
  POST("POST"),
  PUT("PUT"),
  TRACE("TRACE");

  private final String value;

  Method(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static Method get(String stringMethod) {
    for (Method method : values()) {
      if (method.value.equals(stringMethod)) {
        return method;
      }
    }
    return null;
  }
}
