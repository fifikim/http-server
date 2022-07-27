package server.router.routes;

import java.util.ArrayList;
import java.util.List;
import server.constants.Status;
import server.request.Request;
import server.response.Response;

public abstract class Route {
  public abstract String path();

  public abstract List<String> methods();

  public abstract Response processRequest(Request request);

  public String getStatus(Request request) {
    if (methods().contains(request.method())) {
      return Status.OK.toString();
    } else {
      return Status.NOT_ALLOWED.toString();
    }
  }

  public ArrayList<String> getHeaders(Request request) {
    ArrayList<String> headers = new ArrayList<>();
    headers.add(allowHeader(request));

    return headers;
  }

  public String allowHeader(Request request) {
    String joinedMethods = String.join(", ", methods());

    StringBuilder allowHeader = new StringBuilder("Allow: ");
    allowHeader.append(joinedMethods);

    return allowHeader.toString();
  }
}
