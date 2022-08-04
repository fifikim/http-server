package server.router.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import server.constants.Method;
import server.constants.Status;
import server.request.Request;
import server.response.Response;

public abstract class Route implements RouteHandler {
  public Response processRequest(Request request) {
    HashMap<Method, Response> actions = getActions();
    Method method = request.method();

    if (isMethodAllowed(method)) {
      return actions.get(method);
    } else {
      return methodNotAllowed();
    }
  }

  protected abstract List<Method> methodsAllowed();

  protected abstract String body();

  protected abstract Response get();

  protected abstract Response head();

  protected String allowHeader() {
    ArrayList<String> stringMethods = new ArrayList<>();

    for (Method method : methodsAllowed()) {
      stringMethods.add(method.name());
    }

    String joinedMethods = String.join(", ", stringMethods);

    StringBuilder header = new StringBuilder();
    header.append("Allow: ");
    header.append(joinedMethods);

    return header.toString();
  }

  protected String contentLengthHeader() {
    int contentLength = body().getBytes().length;

    StringBuilder header = new StringBuilder();
    header.append("Content-Length: ");
    header.append(contentLength);

    return header.toString();
  }

  private HashMap<Method, Response> getActions() {
    HashMap<Method, Response> actions = new HashMap<>();
    actions.put(Method.GET, get());
    actions.put(Method.HEAD, head());

    return actions;
  }

  private boolean isMethodAllowed(Method method) {
    return methodsAllowed().contains(method);
  }

  private Response methodNotAllowed() {
    List<String> headers = List.of(allowHeader());
    return new Response(Status.NOT_ALLOWED.format(), headers, null);
  }
}