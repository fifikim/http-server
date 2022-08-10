package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGetWithBody implements RouteHandler {
  Set<Method> methodsAllowed = new LinkedHashSet<>(List.of(Method.GET));
  String body = "Hello world";

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    switch (method) {
      case GET -> {
        responseBuilder.setBody(body);
        responseBuilder.addContentLengthHeader(body);
      }
      case HEAD -> responseBuilder.addContentLengthHeader(body);
      default -> {
        responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
        responseBuilder.addAllowHeader(methodsAllowed);
      }
    }

    return responseBuilder.build();
  }
}