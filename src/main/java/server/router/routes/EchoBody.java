package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.Method;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class EchoBody implements RouteHandler {
  Set<Method> methodsAllowed = new LinkedHashSet<>(List.of(Method.POST));

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    if (method == Method.POST) {
      responseBuilder.addContentLengthHeader(request.body());
      responseBuilder.setBody(request.body());
    } else {
      responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
      responseBuilder.addAllowHeader(methodsAllowed);
    }

    return  responseBuilder.build();
  }
}
