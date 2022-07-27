package server.request;

public class RequestParser {
  private final String request;

  public RequestParser(String request) {
    this.request = request;
  }

  public Request parse() {
    return new Request(method(), path(), protocol(), body());
  }

  private String startLine() {
    return request.split("\r\n")[0];
  }

  private String method() {
    return startLine().split(" ")[0];
  }

  private String path() {
    return startLine().split(" ")[1];
  }

  private String protocol() {
    return startLine().split(" ")[2];
  }

  private String body() {
    String[] splitBody = request.split("\r\n\r\n");

    return (splitBody.length > 1) ? splitBody[1] : "";
  }
}
