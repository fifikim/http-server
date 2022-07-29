package server.router.routes;

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
    return List.of("HEAD", "OPTIONS");
  }

  @Override
  public String resource() {
    return null;
  }
}