package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class HtmlResponse extends Route {
  public HtmlResponse(Request request) {
    super(request);
  }

  @Override
  public Path path() {
    return Path.HTML_RESPONSE;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return "<html><body><p>HTML Response</p></body></html>";
  }
}