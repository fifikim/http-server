package server.router.routes;

import java.util.ArrayList;
import java.util.List;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;
import server.constants.Protocol;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;

public abstract class Route {
  protected final Request request;
  protected Status status;
  protected String startLine;
  protected ArrayList<String> headers;
  protected String body;
  protected String newLocation;

  protected Route(Request request) {
    this.request = request;
  }

  public abstract Path path();

  protected abstract List<Method> methods();

  protected abstract String body();

  public Response processRequest() {
    setStatus();
    setStartLine();
    setHeaders();
    setBody();

    return new ResponseBuilder()
            .setStartLine(startLine)
            .setHeaders(headers)
            .setBody(body)
            .build();
  }

  protected void setStatus() {
    if (methodNotAllowed()) {
      status = Status.NOT_ALLOWED;
    } else if (hasNewLocation()) {
      status = Status.MOVED;
    } else {
      status = Status.OK;
    }
  }

  private void setStartLine() {
    setStatus();

    StringBuilder startLine = new StringBuilder();
    startLine.append(Protocol.DEFAULT);
    startLine.append(status);

    this.startLine = startLine.toString();
  }

  private void setHeaders() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add(allowHeader());

    if (responseHasBody()) {
      headers.add(contentLengthHeader());
    }

    if (hasNewLocation()) {
      headers.add(locationHeader());
    }

    this.headers = headers;
  }

  private void setBody() {
    if (responseHasBody()) {
      this.body = body();
    }
  }

  private String allowHeader() {
    ArrayList<String> stringMethods = new ArrayList<>();
    for (Method method : methods()) {
      stringMethods.add(method.name());
    }

    String joinedMethods = String.join(", ", stringMethods);

    StringBuilder header = new StringBuilder();
    header.append(Header.ALLOW.toKey());
    header.append(joinedMethods);

    return header.toString();
  }

  private String contentLengthHeader() {
    int contentLength = body().getBytes().length;

    StringBuilder header = new StringBuilder();
    header.append(Header.CONTENT_LENGTH.toKey());
    header.append(contentLength);

    return header.toString();
  }

  private String locationHeader() {
    StringBuilder locationHeader = new StringBuilder("Location: ");
    locationHeader.append("http://");
    locationHeader.append(request.headers().get("Host"));
    locationHeader.append(newLocation);

    return locationHeader.toString();
  }

  private boolean hasNewLocation() {
    return newLocation != null;
  }

  private boolean methodNotAllowed() {
    return !methods().contains(request.method());
  }

  private boolean responseHasBody() {
    List<Method> noBodyMethods = List.of(Method.CONNECT, Method.HEAD, Method.TRACE);

    boolean bodyRequested = !noBodyMethods.contains(request.method());
    boolean resourceExists = body() != null;
    boolean requestSuccessful = status == Status.OK;

    return bodyRequested && resourceExists && requestSuccessful;
  }
}