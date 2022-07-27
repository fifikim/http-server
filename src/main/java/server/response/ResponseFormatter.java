package server.response;

import java.util.ArrayList;

public class ResponseFormatter {
  private static final String crlf = "\r\n";

  public static String startLine(String protocol, String status) {
    StringBuilder startLine = new StringBuilder();

    startLine.append(protocol);
    startLine.append(" ");
    startLine.append(status);
    startLine.append(crlf);

    return startLine.toString();
  }

  public static String formatHeaders(ArrayList<String> headers) {
    StringBuilder formattedHeaders = new StringBuilder();

    for (String header : headers) {
      formattedHeaders.append(header);
      formattedHeaders.append(crlf);
    }

    return formattedHeaders.toString();
  }

  public static String formatBody(String body) {
    StringBuilder formattedBody = new StringBuilder();

    formattedBody.append(crlf);
    formattedBody.append(body);

    return formattedBody.toString();
  }
}
