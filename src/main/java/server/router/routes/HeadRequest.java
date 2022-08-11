package server.router.routes;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class HeadRequest implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(Arrays.asList(Method.HEAD, Method.OPTIONS));
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