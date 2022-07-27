package server.router.routes;

import java.util.Arrays;
import java.util.List;
import server.request.Request;

public class HeadRequest extends Route {
  public HeadRequest(Request request) {
    super(request);
  }

  @Override
  public String path() {
    return "/head_request";
  }

  @Override
  public List<String> methods() {
    return Arrays.asList("HEAD", "OPTIONS");
  }

  @Override
  public String resource() {
    return null;
  }
}
