package server.router.routes;

import java.util.List;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class NotFound extends Route {
  @Override
  public String path() {
    return null;
  }

  @Override
  public List<String> methods() {
    return null;
  }

  @Override
  public Response processRequest(Request request) {
    return new ResponseBuilder()
                .setStartLine(request.protocol(), Status.NOT_FOUND.toString())
                .build();
  }
}