package server.router;

import java.util.Arrays;
import java.util.List;
import server.request.Request;
import server.response.Response;
import server.router.routes.NotFound;
import server.router.routes.Route;

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
    return Arrays.asList(
            new Route(request, "/simple_get", List.of("GET"), null),
            new Route(request, "/simple_get_with_body", List.of("GET"), "Hello world"),
            new Route(request, "/head_request", Arrays.asList("HEAD", "OPTIONS"), null)
    );
  }
}
