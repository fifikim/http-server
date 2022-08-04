package server.router.routes;

import java.util.List;
import server.constants.Method;
import server.constants.Status;
import server.response.Response;
import server.response.ResponseBuilder;

public class SimpleGetWithBody extends Route {
  @Override
  protected List<Method> methodsAllowed() {
    return List.of(Method.GET, Method.HEAD);
  }

  @Override
  protected String body() {
    return "Hello world";
  }

  @Override
  protected Response get() {
    return new ResponseBuilder()
            .setStartLine(Status.OK.format())
            .setHeaders(List.of(contentLengthHeader()))
            .setBody(body())
            .build();
  }

  @Override
  protected Response head() {
    return null;
  }
}