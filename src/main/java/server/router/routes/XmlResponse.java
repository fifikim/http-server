package server.router.routes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.ContentType;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class XmlResponse implements RouteHandler {
  String body = "<note><body>XML Response</body></note>";

  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    Method method = request.method();

    responseBuilder.addContentTypeHeader(ContentType.XML);
    responseBuilder.addContentLengthHeader(body);

    if (method == Method.GET) {
      responseBuilder.setBody(body);
    }

    return responseBuilder.build();
  }
}