package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class TextResponse extends Route {
  public TextResponse(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.TEXT_RESPONSE;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return "text response";
  }
}