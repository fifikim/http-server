package server.router.routes;

import java.util.List;
import server.request.Request;

public class SimpleGetWithBody extends Route {
  public SimpleGetWithBody(Request request) {
    super(request);
  }

  @Override
  public String path() {
    return "/simple_get_with_body";
  }

  @Override
  public List<String> methods() {
    return List.of("GET");
  }

  @Override
  public String resource() {
    return "Hello world";
  }
}