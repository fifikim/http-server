package server.router.routes;

import java.util.Arrays;
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
    return Arrays.asList("GET");
  }

  @Override
  public String resource() {
    return "Hello world";
  }
}
