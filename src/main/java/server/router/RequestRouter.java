package server.router;

import java.util.List;
import server.request.Request;
import server.response.Response;
import server.router.routes.AllRoutes;
import server.router.routes.NotFound;
import server.router.routes.Route;

public class RequestRouter {
  private final Request request;
  private final String path;
  private final List<Route> routes;

  public RequestRouter(Request request) {
    this.request = request;
    path = request.path();
    routes = AllRoutes.getList();
  }

  public Response getResponse() {
    for (Route route : routes) {
      if (path.equals(route.path())) {
        return route.processRequest(request);
      }
    }
    return new NotFound().processRequest(request);
  }
}
