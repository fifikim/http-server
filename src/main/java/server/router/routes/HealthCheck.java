package server.router.routes;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.constants.ContentType;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.FileReader;

public class HealthCheck implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) throws IOException {
    String body = FileReader.read("/health-check.html");

    ResponseBuilder responseBuilder = new ResponseBuilder();

    responseBuilder.addContentTypeHeader(ContentType.HTML);
    responseBuilder.addContentLengthHeader(body);
    responseBuilder.setBody(body);

    return responseBuilder.build();
  }
}