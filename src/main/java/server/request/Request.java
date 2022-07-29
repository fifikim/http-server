package server.request;

import server.constants.Method;
import server.constants.Path;
import java.util.HashMap;

public record Request(Method method,
                      Path path,
                      HashMap<String, String> headers,
                      String body) {
}
