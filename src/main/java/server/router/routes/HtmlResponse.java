package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.ContentType;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class HtmlResponse implements RouteHandler {
  String body = "<html><body><p>HTML Response</p></body></html>";

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();
    byte[] bodyBytes = body.getBytes();

    responseBuilder.addContentTypeHeader(ContentType.HTML);
    responseBuilder.addContentLengthHeader(bodyBytes.length);

    if (method == Method.GET) {
      responseBuilder.setBody(bodyBytes);
    }

    return responseBuilder.build();
  }
}