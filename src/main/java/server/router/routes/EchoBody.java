package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class EchoBody implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.POST));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();

    responseBuilder.addContentLengthHeader(request.body());
    responseBuilder.setBody(request.body());

    return responseBuilder.build();
  }
}

