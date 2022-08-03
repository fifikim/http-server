package server.router;

import java.util.List;
import server.constants.Protocol;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.routes.EchoBody;
import server.router.routes.HeadRequest;
import server.router.routes.Redirect;
import server.router.routes.Route;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;

public class RequestRouter {
  private Request request;

  public Response getResponse(Request request) {
    this.request = request;
    List<Route> routes = getAllRoutes();

    if (request.path() == null) {
      return errorResponse(Status.NOT_FOUND);
    }

    if (request.method() == null) {
      return errorResponse(Status.BAD_REQUEST);
    }

    for (Route route : routes) {
      if (route.path().equals(request.path())) {
        return route.processRequest();
      }
    }

    return null;
  }

  private List<Route> getAllRoutes() {
    return List.of(
            new HeadRequest(request),
            new SimpleGet(request),
            new SimpleGetWithBody(request),
            new EchoBody(request),
            new Redirect(request)
    );
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
