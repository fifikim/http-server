package server.request;

import java.util.HashMap;
import server.constants.Method;
import server.constants.Path;

public record Request(Method method,
                      Path path,
                      HashMap<String, String> headers,
                      String body) {
}
