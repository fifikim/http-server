package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class HeadRequest extends Route {
  public HeadRequest(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.HEAD_REQUEST;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.HEAD, Method.OPTIONS);
  }

  @Override
  public String body() {
    return null;
  }
}