package server.response;

import java.util.List;

public record Response(String startLine, List<String> headers, byte[] body) {
}
