package server.router.routes;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGet implements RouteHandler {

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(Arrays.asList(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    return  new ResponseBuilder().build();
  }
}