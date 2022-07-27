package server.response;

public class Response {
  private final String startLine;
  private final String headers;
  private final String body;

  public Response(String startLine, String headers, String body) {
    this.startLine = startLine;
    this.headers = headers;
    this.body = body;
  }

  public String toString() {
    StringBuilder response = new StringBuilder();

    response.append(startLine);
    response.append(headers);
    response.append(body);

    return response.toString();
  }
}
