package server.router;

import java.util.HashMap;
import java.util.Set;
import server.constants.Method;
import server.constants.Path;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.routes.EchoBody;
import server.router.routes.HeadRequest;
import server.router.routes.Redirect;
import server.router.routes.RouteHandler;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  public Response getResponse(Request request) {
    HashMap<Path, RouteHandler> routes = getAllRoutes();
    RouteHandler route = routes.get(request.path());

    if (request.method() == null) {
      return new Response(Status.BAD_REQUEST.format(), null, null);
    }

    if (route != null) {
      Set<Method> methodsAllowed = route.getMethods();

      if (methodsAllowed.contains(request.method())) {
        return route.processRequest(request);
      } else {
        return methodNotAllowed(methodsAllowed);
      }
    }

    return new Response(Status.NOT_FOUND.format(), null, null);
  }

  private static HashMap<Path, RouteHandler> getAllRoutes() {
    HashMap<Path, RouteHandler> routes = new HashMap<>();
    routes.put(Path.SIMPLE_GET, new SimpleGet());
    routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody());
    routes.put(Path.HEAD_REQUEST, new HeadRequest());
    routes.put(Path.ECHO_BODY, new EchoBody());
    routes.put(Path.REDIRECT, new Redirect());

    return routes;
  }

  private Response methodNotAllowed(Set<Method> methodsAllowed) {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setStartLine(Status.NOT_ALLOWED.format());
    responseBuilder.addAllowHeader(methodsAllowed);

    return responseBuilder.build();
  }
}
