package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class MethodOptions implements RouteHandler {
  Set<Method> methodsAllowed = new LinkedHashSet<>(List.of(
          Method.GET, Method.HEAD, Method.OPTIONS));

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    switch (method) {
      case GET, HEAD -> {
      }
      case OPTIONS -> {
        responseBuilder.addAllowHeader(methodsAllowed);
      }
      default -> {
        responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
        responseBuilder.addAllowHeader(methodsAllowed);
      }
    }

    return responseBuilder.build();
  }
}