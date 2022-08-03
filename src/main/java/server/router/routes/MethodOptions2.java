package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class MethodOptions2 extends Route {
  public MethodOptions2(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.METHOD_OPTIONS2;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET, Method.HEAD, Method.OPTIONS, Method.POST, Method.PUT);
  }

  @Override
  public String body() {
    return null;
  }
}