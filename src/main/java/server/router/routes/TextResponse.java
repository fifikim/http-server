package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.ContentType;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class TextResponse implements RouteHandler {
  String body = "text response";

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();
    byte[] bodyBytes = body.getBytes();

    responseBuilder.addContentTypeHeader(ContentType.TEXT);
    responseBuilder.addContentLengthHeader(bodyBytes.length);

    if (method == Method.GET) {
      responseBuilder.setBody(bodyBytes);
    }

    return responseBuilder.build();
  }
}