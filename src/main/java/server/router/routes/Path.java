package server.router.routes;

import java.util.List;
import server.request.Request;

public class Path extends Route {
  private final String path;
  private final List<String> methods;
  private final String resource;

  public Path(Request request, String path, List<String> methods, String resource) {
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
