package server.response;

import java.util.List;
import server.constants.Format;

public class ResponseFormatter {
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
    formattedStartLine.append(Format.BREAK);

    return formattedStartLine.toString();
  }

  private static String formatHeaders(List<String> headers) {
    if (headers == null) {
      return "";
    }

    StringBuilder formattedHeaders = new StringBuilder();
    for (String header : headers) {
      formattedHeaders.append(header);
      formattedHeaders.append(Format.BREAK);
    }

    return formattedHeaders.toString();
  }

  private static String formatBody(String body) {
    StringBuilder formattedBody = new StringBuilder();
    formattedBody.append(Format.BREAK);

    if (body != null) {
      formattedBody.append(body);
    }

    return formattedBody.toString();
  }
}
