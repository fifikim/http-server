package server.response;

import java.util.ArrayList;

public class ResponseFormatter {
  private static final String crlf = "\r\n";

  public static String startLine(String startLine) {
    StringBuilder formattedStartLine = new StringBuilder();

    formattedStartLine.append(startLine);
    formattedStartLine.append(crlf);

    return formattedStartLine.toString();
  }

  public static String headers(ArrayList<String> headers) {
    if (headers == null) {
      return "";
    }

    StringBuilder formattedHeaders = new StringBuilder();

    for (String header : headers) {
      formattedHeaders.append(header);
      formattedHeaders.append(crlf);
    }

    return formattedHeaders.toString();
  }

  public static String body(String body) {
    if (body == null) {
      return crlf;
    }

    StringBuilder formattedBody = new StringBuilder();

    formattedBody.append(crlf);
    formattedBody.append(body);

    return formattedBody.toString();
  }
}
