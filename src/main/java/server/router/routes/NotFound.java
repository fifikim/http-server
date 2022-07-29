package server.router.routes;

import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class NotFound {
  private final Request request;

  public NotFound(Request request) {
    this.request = request;
  }

  public Response processRequest() {
    StringBuilder startLine = new StringBuilder();
    startLine.append(request.protocol());
    startLine.append(" ");
    startLine.append(Status.NOT_FOUND);

    return new ResponseBuilder()
                .setStartLine(startLine.toString())
                .setHeaders(null)
                .setBody(null)
                .build();
  }
}