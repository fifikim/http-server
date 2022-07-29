package server.request;

import java.util.Arrays;

public class RequestParser {
  private String rawRequest;

  public Request parse(String rawRequest) {
    this.rawRequest = rawRequest;

    return new Request(method(), path(), protocol(), body());
  }

  private String startLine() {
    return rawRequest.split("\r\n")[0];
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
    String[] splitBody = rawRequest.split("\r\n\r\n");

    if (splitBody.length > 1) {
      String[] bodyLines = Arrays.copyOfRange(splitBody, 1, splitBody.length);
      return String.join("\r\n\r\n", bodyLines);
    } else {
      return "";
    }
  }
}
