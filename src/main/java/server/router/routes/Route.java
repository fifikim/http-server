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

  protected Route(Request request) {
    this.request = request;
  }

  public abstract Path path();

  protected abstract List<Method> methods();

  protected abstract String body();

  public Response processRequest() {
    return new ResponseBuilder()
            .setStartLine(setStartLine())
            .setHeaders(setHeaders())
            .setBody(setBody())
            .build();
  }

  private void setStatus() {
    if (methodNotAllowed()) {
      status = Status.NOT_ALLOWED;
    } else {
      status = Status.OK;
    }
  }

  private String setStartLine() {
    setStatus();

    StringBuilder startLine = new StringBuilder();
    startLine.append(Protocol.DEFAULT);
    startLine.append(status);

    return startLine.toString();
  }

  private ArrayList<String> setHeaders() {
    ArrayList<String> headers = new ArrayList<>();
    headers.add(allowHeader());

    if (responseHasBody()) {
      headers.add(contentLengthHeader());
    }

    return headers;
  }

  private String setBody() {
    return responseHasBody() ? body() : null;
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