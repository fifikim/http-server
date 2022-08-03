package server.request;

import java.util.HashMap;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;

public class RequestBuilder {
  private Method method;
  private Path path;
  private HashMap<Header, String> headers;
  private String body;

  public RequestBuilder setMethod(Method method) {
    this.method = method;
    return this;
  }

  public RequestBuilder setPath(Path path) {
    this.path = path;
    return this;
  }

  public RequestBuilder setHeaders(HashMap<Header, String> headers) {
    this.headers = headers;
    return this;
  }

  public RequestBuilder setBody(String body) {
    this.body = body;
    return this;
  }

  public Request build() {
    return new Request(method, path, headers, body);
  }
}
