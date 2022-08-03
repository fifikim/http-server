package server.router.routes;

import java.util.List;
import server.constants.ContentType;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class SimpleGetWithBody extends Route {
  public SimpleGetWithBody(Request request) {
    super(request);
    this.contentType = ContentType.TEXT;
  }

  @Override
  public Path path() {
    return Path.SIMPLE_GET_WITH_BODY;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return "Hello world";
  }
}