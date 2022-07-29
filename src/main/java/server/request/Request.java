package server.request;

import server.constants.Method;
import server.constants.Path;

public record Request(Method method, Path path, String body) {
}
