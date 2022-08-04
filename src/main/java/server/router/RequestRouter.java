package server.router;

import java.util.HashMap;
import server.constants.Path;
import server.constants.Protocol;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.routes.HeadRequest;
import server.router.routes.Route;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  private Request request;

  public Response getResponse(Request request) {
    this.request = request;
    HashMap<Path, Route> routes = getAllRoutes();

    if (request.path() == null) {
      return errorResponse(Status.NOT_FOUND);
    }

    if (request.method() == null) {
      return errorResponse(Status.BAD_REQUEST);
    }

    Route route = routes.get(request.path());
    if (route != null) {
      return route.processRequest();
    }

    return null;
  }

  private HashMap<Path, Route> getAllRoutes() {
    HashMap<Path, Route> routes = new HashMap<>();
    routes.put(Path.SIMPLE_GET, new SimpleGet(request));
    routes.put(Path.SIMPLE_GET_WITH_BODY, new SimpleGetWithBody(request));
    routes.put(Path.HEAD_REQUEST, new HeadRequest(request));

    return routes;
  }

  private Response errorResponse(Status status) {
    StringBuilder startLine = new StringBuilder();
    startLine.append(Protocol.DEFAULT);
    startLine.append(status);

    return new ResponseBuilder()
            .setStartLine(startLine.toString())
            .build();
  }
}
