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
  Set<Method> methodsAllowed = new LinkedHashSet<>(List.of(Method.GET));
  Path newLocation = Path.SIMPLE_GET;

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();
    String host = request.headers().get("Host");

    switch (method) {
      case GET, HEAD -> {
        responseBuilder.setStartLine(Status.MOVED.format());
        responseBuilder.addLocationHeader(host, newLocation);
      }
      default -> {
        responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
        responseBuilder.addAllowHeader(methodsAllowed);
      }
    }

    return responseBuilder.build();
  }
}