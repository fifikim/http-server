package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.constants.Path;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class Redirect implements RouteHandler {
  Path newLocation = Path.SIMPLE_GET;

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    String host = request.headers().get("Host");

    responseBuilder.setStartLine(Status.MOVED.format());
    responseBuilder.addLocationHeader(host, newLocation);

    return responseBuilder.build();
  }
}