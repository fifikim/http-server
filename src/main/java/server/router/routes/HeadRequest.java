package server.router.routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public class HeadRequest extends Route {
  @Override
  public String path() {
    return "/head_request";
  }

  @Override
  public List<String> methods() {
    return Arrays.asList("HEAD", "OPTIONS");
  }

  @Override
  public Response processRequest(Request request) {
    String status = getStatus(request);
    ArrayList<String> headers = getHeaders(request);
    String body = "";

    return new ResponseBuilder()
                .setStartLine(request.protocol(), status)
                .setHeaders(headers)
                .setBody(body)
                .build();
  }
}
