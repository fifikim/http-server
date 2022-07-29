package server.router.routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public abstract class Route {
  protected final Request request;
  protected Status status;

  protected Route(Request request) {
    this.request = request;
  }

  public abstract String path();

  protected abstract List<String> methods();

  protected abstract String resource();

  public Response processRequest() {
    return new ResponseBuilder()
            .setStartLine(getStartLine())
            .setHeaders(getHeaders())
            .setBody(getBody())
            .build();
  }

  protected String getStartLine() {
    StringBuilder startLine = new StringBuilder();
    startLine.append(request.protocol());
    startLine.append(" ");

    if (methods().contains(request.method())) {
      status = Status.OK;
    } else {
      status = Status.NOT_ALLOWED;
    }

    startLine.append(status);

    return startLine.toString();
  }

  private ArrayList<String> getHeaders() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add(allowHeader());

    if (responseHasBody()) {
      headers.add(contentLengthHeader());
    }

    return headers;
  }

  private String getBody() {
    return responseHasBody() ? resource() : null;
  }

  private String allowHeader() {
    String joinedMethods = String.join(", ", methods());

    StringBuilder allowHeader = new StringBuilder("Allow: ");
    allowHeader.append(joinedMethods);

    return allowHeader.toString();
  }

  private String contentLengthHeader() {
    int contentLength = resource().getBytes().length;

    StringBuilder contentLengthHeader = new StringBuilder("Content-Length: ");
    contentLengthHeader.append(contentLength);

    return contentLengthHeader.toString();
  }

  private boolean responseHasBody() {
    String[] noBodyMethods = {"HEAD", "TRACE", "CONNECT"};

    boolean bodyRequested = !Arrays.asList(noBodyMethods).contains(request.method());
    boolean resourceExists = resource() != null;
    boolean requestSuccessful = status == Status.OK;

    return bodyRequested && resourceExists && requestSuccessful;
  }
}