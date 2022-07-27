package server.router;

import java.util.Arrays;
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
  private List<Route> routes;

  public Response getResponse(Request request) {
    this.request = request;
    routes = getRouteList();

    for (Route route : routes) {
      if (request.path().equals(route.path())) {
        return route.processRequest();
      }
    }
    return new NotFound(request).processRequest();
  }

  private List<Route> getRouteList() {
    return Arrays.asList(
            new SimpleGet(request),
            new SimpleGetWithBody(request),
            new HeadRequest(request)
    );
  }
}
