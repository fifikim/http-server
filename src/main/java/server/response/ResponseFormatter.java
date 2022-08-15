package server.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import server.constants.Format;

public class ResponseFormatter {
  public static byte[] toBytes(Response response) throws IOException {
    String startLine = formatStartLine(response.startLine());
    String headers = formatHeaders(response.headers());

    StringBuilder responseHead = new StringBuilder();
    responseHead.append(startLine);
    responseHead.append(headers);
    responseHead.append(Format.BREAK);

    byte[] formattedResponseHead = responseHead.toString().getBytes();

    if (response.body() == null) {
      return formattedResponseHead;
    }

    ByteArrayOutputStream responseWithBody = new ByteArrayOutputStream();
    responseWithBody.write(formattedResponseHead);
    responseWithBody.write(response.body());
    return responseWithBody.toByteArray();
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
}
