package server.request;

import java.util.Arrays;
import server.constants.Format;
import server.constants.Method;
import server.constants.Path;
import java.util.HashMap;

public class RequestParser {
  private String rawRequest;

  public Request parse(String rawRequest) {
    this.rawRequest = rawRequest;

    return new Request(method(), path(), headers(), body());
  }

  private String startLine() {
    return rawRequest.split(Format.BREAK)[0];
  }

  private Method method() {
    String stringMethod = startLine().split(" ")[0];
    return Method.get(stringMethod);
  }

  private Path path() {
    String stringPath = startLine().split(" ")[1];
    return Path.get(stringPath);
  }

  private HashMap<String, String> headers() {
    String requestHead = rawRequest.split("\r\n\r\n")[0];
    String[] headLines = requestHead.split("\r\n");

    HashMap<String, String> mappedHeaders = new HashMap<>();
    String[] headers = Arrays.copyOfRange(headLines, 1, headLines.length);

    for (String line : headers) {
      if (!line.isBlank()) {
        String[] lineData = line.split(": ");
        mappedHeaders.put(lineData[0], lineData[1]);
      }
    }

    return mappedHeaders;
  }

  private String body() {
    String[] splitBody = rawRequest.split(Format.DBL_BREAK);

    if (splitBody.length > 1) {
      String[] bodyLines = Arrays.copyOfRange(splitBody, 1, splitBody.length);
      return String.join(Format.DBL_BREAK, bodyLines);
    } else {
      return null;
    }
  }
}
