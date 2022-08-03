package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class SimpleGet extends Route {
  public SimpleGet(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.SIMPLE_GET;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET, Method.HEAD);
  }

  @Override
  public String body() {
    return null;
  }
}