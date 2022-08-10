package server.router.routes;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import server.constants.Method;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGet implements RouteHandler {
  Set<Method> methodsAllowed = new LinkedHashSet<>(Arrays.asList(Method.GET, Method.HEAD));

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    switch (method) {
      case GET:
      case HEAD:
        break;
      default:
        responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
        responseBuilder.addAllowHeader(methodsAllowed);
    }

    return  responseBuilder.build();
  }
}