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

public class DoggoPng implements RouteHandler {
  @Override
  public Set<Method> getMethods() {
    return new LinkedHashSet<>(List.of(Method.GET, Method.HEAD));
  }

  @Override
  public Response processRequest(Request request) throws IOException {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    byte[] body = FileReader.readBytes("/doggo.png");

    responseBuilder.addContentTypeHeader(ContentType.PNG);
    responseBuilder.addContentLengthHeader(body.length);
    responseBuilder.setBody(body);

    return responseBuilder.build();
  }
}