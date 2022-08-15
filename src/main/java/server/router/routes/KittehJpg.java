package server.router.routes;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import server.FileReader;
import server.constants.ContentType;
import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class KittehJpg implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) throws IOException {
    byte[] body = FileReader.readBytes("/kitteh.jpg");

    ResponseBuilder responseBuilder = new ResponseBuilder();

    responseBuilder.addContentTypeHeader(ContentType.JPG);
    responseBuilder.addContentLengthHeader(body.length);

    if (request.method() == Method.GET) {
      responseBuilder.setBody(body);
    }

    return responseBuilder.build();
  }
}