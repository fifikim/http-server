package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class MethodOptions extends Route {
  public MethodOptions(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.METHOD_OPTIONS;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET, Method.HEAD, Method.OPTIONS);
  }

  @Override
  public String body() {
    return null;
  }
}