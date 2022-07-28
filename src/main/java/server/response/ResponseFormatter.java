package server.response;

import java.util.ArrayList;

public class ResponseFormatter {
  private static final String CRLF = "\r\n";

  public static String toString(Response response) {
    String startLine = formatStartLine(response.startLine());
    String headers = formatHeaders(response.headers());
    String body = formatBody(response.body());

    StringBuilder formattedResponse = new StringBuilder();

    formattedResponse.append(startLine);
    formattedResponse.append(headers);
    formattedResponse.append(body);

    return formattedResponse.toString();
  }

  private static String formatStartLine(String startLine) {
    StringBuilder formattedStartLine = new StringBuilder();

    formattedStartLine.append(startLine);
    formattedStartLine.append(CRLF);

    return formattedStartLine.toString();
  }

  private static String formatHeaders(ArrayList<String> headers) {
    if (headers == null) {
      return "";
    }

    StringBuilder formattedHeaders = new StringBuilder();

    for (String header : headers) {
      formattedHeaders.append(header);
      formattedHeaders.append(CRLF);
    }

    return formattedHeaders.toString();
  }

  private static String formatBody(String body) {
    if (body == null) {
      return CRLF;
    }

    StringBuilder formattedBody = new StringBuilder();

    formattedBody.append(CRLF);
    formattedBody.append(body);

    return formattedBody.toString();
  }
}
