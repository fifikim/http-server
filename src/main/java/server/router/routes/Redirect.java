package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class Redirect extends Route {
  public Redirect(Request request) {
    super(request);
    this.newLocation = "/simple_get";
  }

  @Override
  public Path path() {
    return Path.REDIRECT;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return null;
  }
}