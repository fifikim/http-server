package server.router.routes;

import java.util.List;
import server.request.Request;

public class SimpleGet extends Route {
  public SimpleGet(Request request) {
    super(request);
  }

  @Override
  public String path() {
    return "/simple_get";
  }

  @Override
  public List<String> methods() {
    return List.of("GET");
  }

  @Override
  public String resource() {
    return null;
  }
}