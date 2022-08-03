package server.router.routes;

import java.util.List;
import server.constants.ContentType;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class JsonResponse extends Route {
  public JsonResponse(Request request) {
    super(request);
    this.contentType = ContentType.JSON;
  }

  @Override
  public Path path() {
    return Path.JSON_RESPONSE;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return "{\"key1\":\"value1\",\"key2\":\"value2\"}";
  }
}