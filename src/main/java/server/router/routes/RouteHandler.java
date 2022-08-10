package server.router.routes;

import server.request.Request;
import server.response.Response;

public interface RouteHandler {
  Response processRequest(Request request);
}
