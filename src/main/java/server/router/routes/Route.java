package server.router.routes;

import java.util.List;
import server.request.Request;

public class Route extends RouteTemplate {
  private final String path;
  private final List<String> methods;
  private final String resource;

  public Route(Request request, String path, List<String> methods, String resource) {
    super(request);
    this.path = path;
    this.methods = methods;
    this.resource = resource;
  }

  @Override
  public String path() {
    return this.path;
  }

  @Override
  public List<String> methods() {
    return this.methods;
  }

  @Override
  public String resource() {
    return this.resource;
  }
}
