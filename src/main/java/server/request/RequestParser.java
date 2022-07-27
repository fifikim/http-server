package server.request;

public class RequestParser {
  private String request;

  public Request parse(String request) {
    this.request = request;

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
