package server.router;

import java.util.ArrayList;
import java.util.List;
import server.constants.ContentType;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;

public class HeadersBuilder {
  private String allow = "";
  private String contentType = "";
  private String contentLength = "";
  private String location = "";

  public void addAllow(List<Method> methods) {
    ArrayList<String> stringMethods = new ArrayList<>();
    for (Method method : methods) {
      stringMethods.add(method.name());
    }

    String joinedMethods = String.join(", ", stringMethods);

    StringBuilder header = new StringBuilder();
    header.append(Header.ALLOW.toKey());
    header.append(joinedMethods);

    this.allow = header.toString();
  }

  public void addContentType(ContentType contentType) {
    StringBuilder header = new StringBuilder();
    header.append(Header.CONTENT_TYPE.toKey());
    header.append(contentType);

    this.contentType = header.toString();
  }

  public void addContentLength(String body) {
    int contentLength = body.getBytes().length;

    StringBuilder header = new StringBuilder();
    header.append(Header.CONTENT_LENGTH.toKey());
    header.append(contentLength);

    this.contentLength = header.toString();
  }

  public void addLocation(String host, Path newLocation) {
    StringBuilder locationHeader = new StringBuilder();
    locationHeader.append(Header.LOCATION.toKey());
    locationHeader.append("http://");
    locationHeader.append(host);
    locationHeader.append(newLocation);

    this.location = locationHeader.toString();
  }

  public ArrayList<String> build() {
    List<String> headerTypes = List.of(allow, contentType, contentLength, location);
    ArrayList<String> headers = new ArrayList<>();

    for (String header : headerTypes) {
      if (!header.isBlank()) {
        headers.add(header);
      }
    }

    return (headers.isEmpty()) ? null : headers;
  }
}
