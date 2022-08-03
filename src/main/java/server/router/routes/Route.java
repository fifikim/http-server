package server.router.routes;

import java.util.ArrayList;
import java.util.List;
import server.constants.ContentType;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;
import server.constants.Protocol;
import server.constants.Status;
import server.request.Request;
import server.response.Response;
import server.response.ResponseBuilder;
import server.router.HeadersBuilder;

public abstract class Route {
  protected final Request request;
  protected Status status;
  protected String startLine;
  protected ArrayList<String> headers;
  protected String body;
  protected Path newLocation;
  protected ContentType contentType;

  protected Route(Request request) {
    this.request = request;
  }

  public abstract Path path();

  protected abstract List<Method> methods();

  protected abstract String body();

  public Response processRequest() {
    setStatus();
    setStartLine();
    setBody();

    setHeaders();

    return new ResponseBuilder()
            .setStartLine(startLine)
            .setHeaders(headers)
            .setBody(body)
            .build();
  }

  private void setStatus() {
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

  private void setBody() {
    if (responseHasBody()) {
      this.body = body();
    }
  }

  private void setHeaders() {
    HeadersBuilder headers = new HeadersBuilder();

    if (hasAllowHeader()) {
      headers.addAllow(methods());
    }

    if (responseHasBody()) {
      headers.addContentType(contentType);
      headers.addContentLength(body);
    }

    if (hasNewLocation()) {
      String host = request.headers().get(Header.HOST);
      headers.addLocation(host, newLocation);
    }

    this.headers = headers.build();
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

  private boolean hasAllowHeader() {
    return request.method().equals(Method.OPTIONS) || status == Status.NOT_ALLOWED;
  }

  private boolean hasNewLocation() {
    return newLocation != null;
  }
}