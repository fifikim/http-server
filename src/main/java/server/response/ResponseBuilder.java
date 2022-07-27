package server.response;

import java.util.ArrayList;

public class ResponseBuilder {
  private String startLine;
  private ArrayList<String> headers;
  private String body = "\r\n";

  public ResponseBuilder setStartLine(String startLine) {
    this.startLine = startLine;
    return this;
  }

  public ResponseBuilder setHeaders(ArrayList<String> headers) {
    this.headers = headers;
    return this;
  }

  public ResponseBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public Response build() {
    return new Response(startLine, headers, body);
  }
}
