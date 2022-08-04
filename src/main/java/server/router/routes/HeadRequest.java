package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Status;
import server.response.Response;
import server.response.ResponseBuilder;

public class HeadRequest extends Route {
  @Override
  public List<Method> methodsAllowed() {
    return List.of(Method.HEAD, Method.OPTIONS);
  }

  @Override
  public String body() {
    return null;
  }

  @Override
  protected Response get() {
    return null;
  }

  @Override
  protected Response head() {
    return new ResponseBuilder()
            .setStartLine(Status.OK.format())
            .setHeaders(List.of(allowHeader()))
            .build();
  }

}