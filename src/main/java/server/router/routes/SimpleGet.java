package server.router.routes;

import java.util.Arrays;
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
    return Arrays.asList("GET");
  }

  @Override
  public String resource() {
    return null;
  }
}
