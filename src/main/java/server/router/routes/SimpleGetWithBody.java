package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGetWithBody implements RouteHandler {
  String body = "Hello world";

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    if (method == Method.GET) {
      responseBuilder.setBody(body);
      responseBuilder.addContentLengthHeader(body);
    }

    if (method == Method.HEAD) {
      responseBuilder.addContentLengthHeader(body);
    }

    return responseBuilder.build();
  }
}