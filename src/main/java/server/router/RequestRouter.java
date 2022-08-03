package server.router;

import java.util.List;
import server.constants.Protocol;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.routes.EchoBody;
import server.router.routes.HeadRequest;
import server.router.routes.HtmlResponse;
import server.router.routes.JsonResponse;
import server.router.routes.MethodOptions;
import server.router.routes.MethodOptions2;
import server.router.routes.Redirect;
import server.router.routes.Route;
import server.router.routes.SimpleGet;
import server.router.routes.SimpleGetWithBody;
import server.router.routes.TextResponse;
import server.router.routes.XmlResponse;

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
            new EchoBody(request),
            new HeadRequest(request),
            new HtmlResponse(request),
            new JsonResponse(request),
            new MethodOptions(request),
            new MethodOptions2(request),
            new Redirect(request),
            new SimpleGet(request),
            new SimpleGetWithBody(request),
            new TextResponse(request),
            new XmlResponse(request)
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
