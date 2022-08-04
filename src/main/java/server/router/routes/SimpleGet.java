package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Status;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGet extends Route {
  @Override
  protected List<Method> methodsAllowed() {
    return List.of(Method.GET, Method.HEAD);
  }

  @Override
  protected String body() {
    return null;
  }

  @Override
  protected Response get() {
    return new ResponseBuilder()
            .setStartLine(Status.OK.format())
            .build();
  }

  @Override
  protected Response head() {
    return new ResponseBuilder()
            .setStartLine(Status.OK.format())
            .setHeaders(List.of(allowHeader()))
            .build();
  }
}