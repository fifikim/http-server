package server.response;

import java.util.ArrayList;

public class ResponseBuilder {
  private String startLine;
  private String headers = "";
  private String body = "\r\n";

  public ResponseBuilder setStartLine(String protocol, String status) {
    startLine = ResponseFormatter.startLine(protocol, status);
    return this;
  }

  public ResponseBuilder setHeaders(ArrayList<String> headerList) {
    headers = ResponseFormatter.formatHeaders(headerList);
    return this;
  }

  public ResponseBuilder setBody(String body) {
    this.body = ResponseFormatter.formatBody(body);
    return this;
  }

  public Response build() {
    return new Response(startLine, headers, body);
  }
}
