package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class MethodOptions2 implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(
            Method.GET, Method.HEAD, Method.OPTIONS, Method.POST, Method.PUT));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    if (method == Method.OPTIONS) {
      responseBuilder.addAllowHeader(getMethods());
    }

    return responseBuilder.build();
  }
}