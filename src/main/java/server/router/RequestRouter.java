package server.router;

import java.util.HashMap;
import server.constants.Path;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.routes.HeadRequest;
import server.router.routes.RouteHandler;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  public Response getResponse(Request request) {
    HashMap<Path, RouteHandler> routes = getAllRoutes();
    RouteHandler route = routes.get(request.path());

    if (request.path() == null) {
      return errorResponse(Status.NOT_FOUND);
    }

    if (request.method() == null) {
      return errorResponse(Status.BAD_REQUEST);
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

  private Response errorResponse(Status status) {
    return new ResponseBuilder()
            .setStartLine(status.format())
            .build();
  }
}
