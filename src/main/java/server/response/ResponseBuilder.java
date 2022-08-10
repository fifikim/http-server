package server.response;

import java.util.ArrayList;
import java.util.Set;
import server.constants.Method;
import server.constants.Path;
import server.constants.Status;

public class ResponseBuilder {
  private String startLine = Status.OK.format();
  private ArrayList<String> headers = new ArrayList<>();
  private String body;

  public void setStartLine(String startLine) {
    this.startLine = startLine;
  }

  public void addAllowHeader(Set<Method> methods) {
    ArrayList<String> stringMethods = new ArrayList<>();
    for (Method method : methods) {
      stringMethods.add(method.name());
    }

    String joinedMethods = String.join(", ", stringMethods);

    StringBuilder header = new StringBuilder();
    header.append("Allow: ");
    header.append(joinedMethods);

    headers.add(header.toString());
  }

  public void addContentLengthHeader(String body) {
    int contentLength = body.getBytes().length;

    StringBuilder header = new StringBuilder();
    header.append("Content-Length: ");
    header.append(contentLength);

    headers.add(header.toString());
  }

  public void addLocationHeader(String host, Path newLocation) {
    StringBuilder header = new StringBuilder();
    header.append("Location: http://");
    header.append(host);
    header.append(newLocation);

    headers.add(header.toString());
  }

  public ResponseBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public Response build() {
    if (headers.isEmpty()) {
      headers = null;
    }

    return new Response(startLine, headers, body);
  }
}
