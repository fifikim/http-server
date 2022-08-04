package server.router;

import java.util.HashMap;
import server.constants.Path;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.router.routes.HeadRequest;
import server.router.routes.RouteHandler;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  public Response getResponse(Request request) {
    HashMap<Path, RouteHandler> routes = getAllRoutes();
    RouteHandler route = routes.get(request.path());

    if (request.path() == null) {
      return new Response(Status.NOT_FOUND.format(), null, null);
    }

    if (request.method() == null) {
      return new Response(Status.BAD_REQUEST.format(), null, null);
    }

    if (route != null) {
      return route.processRequest(request);
    }

    return null;
  }

  private static HashMap<Path, RouteHandler> getAllRoutes() {
    HashMap<Path, RouteHandler> routes = new HashMap<>();
    routes.put(Path.SIMPLE_GET, new SimpleGet());
    routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody());
    routes.put(Path.HEAD_REQUEST, new HeadRequest());

    return routes;
  }
}
