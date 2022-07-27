package server.response;

import java.util.ArrayList;

public class Response {
  private final String startLine;
  private final ArrayList<String> headers;
  private final String body;

  public Response(String startLine, ArrayList<String> headers, String body) {
    this.startLine = startLine;
    this.headers = headers;
    this.body = body;
  }

  public String format() {
    StringBuilder response = new StringBuilder();

    response.append(ResponseFormatter.startLine(startLine));
    response.append(ResponseFormatter.headers(headers));
    response.append(ResponseFormatter.body(body));

    return response.toString();
  }
}
