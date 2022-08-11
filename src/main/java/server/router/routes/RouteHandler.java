package server.router.routes;

import java.util.Set;
import server.constants.Method;
import server.request.Request;
import server.response.Response;

public interface RouteHandler {
  Set<Method> getMethods();

  Response processRequest(Request request);
}
