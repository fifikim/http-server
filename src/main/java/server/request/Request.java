package server.request;

public record Request(String method, String path, String protocol, String body) {
}
