package server.router.routes;

import java.util.List;
import server.constants.ContentType;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class EchoBody extends Route {
  public EchoBody(Request request) {
    super(request);
    this.contentType = ContentType.TEXT;
  }

  @Override
  public Path path() {
    return Path.ECHO_BODY;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.POST);
  }

  @Override
  public String body() {
    return request.body();
  }
}