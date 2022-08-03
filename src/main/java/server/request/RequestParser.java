package server.request;

import java.util.Arrays;
import java.util.HashMap;
import server.constants.Format;
import server.constants.Header;
import server.constants.Method;
import server.constants.Path;

public class RequestParser {
  private final String rawRequest;

  public RequestParser(String rawRequest) {
    this.rawRequest = rawRequest;
  }

  public Method method() {
    String stringMethod = startLine().split(" ")[0];
    return Method.get(stringMethod);
  }

  public Path path() {
    String stringPath = startLine().split(" ")[1];
    return Path.get(stringPath);
  }

  public HashMap<Header, String> headers() {
    String requestHead = rawRequest.split(Format.DBL_BREAK)[0];
    String[] headLines = requestHead.split(Format.BREAK);

    HashMap<Header, String> mappedHeaders = new HashMap<>();
    String[] headers = Arrays.copyOfRange(headLines, 1, headLines.length);

    for (String line : headers) {
      if (!line.isBlank()) {
        String[] lineData = line.split(": ");
        Header headerType = Header.get(lineData[0]);
        mappedHeaders.put(headerType, lineData[1]);
      }
    }

    return mappedHeaders;
  }

  private String startLine() {
    return rawRequest.split(Format.BREAK)[0];
  }
}
