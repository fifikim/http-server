package server.router;

import java.util.List;
import server.request.Request;
import server.response.Response;
import server.router.routes.HeadRequest;
import server.router.routes.NotFound;
import server.router.routes.Route;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  private Request request;

  public Response getResponse(Request request) {
    this.request = request;
    List<Route> routes = getAllRoutes();

    for (Route route : routes) {
      if (request.path().equals(route.path())) {
        return route.processRequest();
      }
    }
    return new NotFound(request).processRequest();
  }

  private List<Route> getAllRoutes() {
    return List.of(
            new SimpleGet(request),
            new SimpleGetWithBody(request),
            new HeadRequest(request)
    );
  }
}
