package server.response;

import java.util.ArrayList;

public record Response(String startLine, ArrayList<String> headers, String body) {
}
