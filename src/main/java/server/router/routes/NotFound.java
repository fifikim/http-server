package server.router.routes;

import java.util.List;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class NotFound extends RouteTemplate {
  public NotFound(Request request) {
    super(request);
  }

  @Override
  public String path() {
    return null;
  }

  @Override
  public List<String> methods() {
    return null;
  }

  @Override
  public String resource() {
    return null;
  }

  @Override
  public Response processRequest() {
    StringBuilder startLine = new StringBuilder();
    startLine.append(request.protocol());
    startLine.append(" ");
    startLine.append(Status.NOT_FOUND.toString());

    return new ResponseBuilder()
                .setStartLine(startLine.toString())
                .setHeaders(null)
                .setBody(null)
                .build();
  }
}