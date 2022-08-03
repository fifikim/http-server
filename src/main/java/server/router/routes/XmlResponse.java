package server.router.routes;

import java.util.List;
import server.constants.ContentType;
import server.constants.Method;
import server.constants.Path;
import server.request.Request;

public class XmlResponse extends Route {
  public XmlResponse(Request request) {
    super(request);
    this.contentType = ContentType.XML;
  }

  @Override
  public Path path() {
    return Path.XML_RESPONSE;
  }

  @Override
  public List<Method> methods() {
    return List.of(Method.GET);
  }

  @Override
  public String body() {
    return "<note><body>XML Response</body></note>";
  }
}