//package server;
//
//import static org.junit.Assert.assertEquals;
//
//import server.TestHelpers;
//import java.util.HashMap;
//
//import httpserver.server.request.RequestParser;
//import org.junit.Test;
//
//public class RequestParserTest {
//
//  @Test
//  public void getStartLineReturnsFirstLineOfRequestAsHashMap() {
//    String testRequest = TestHelpers.getRequestHead();
//
//    RequestParser parser = new RequestParser(testRequest);
//    HashMap<String, String> startLine = parser.getStartLine();
//
//    assertEquals("GET", startLine.get("method"));
//    assertEquals("/simple_get", startLine.get("path"));
//    assertEquals("HTTP/1.1", startLine.get("protocol"));
//  }
//
//  @Test
//  public void getHeadersReturnsHeadersAsHashMap() {
//    String testRequest = TestHelpers.getRequestHead();
//
//    RequestParser parser = new RequestParser(testRequest);
//    //HashMap<String, String> headers = parser.getHeaders();
//
//    assertEquals("PostmanRuntime/7.29.2", headers.get("User-Agent"));
//    assertEquals("*/*", headers.get("Accept"));
//    assertEquals("8137a6c3-68fc-45e8-9d4c-d235619a034e", headers.get("Postman-Token"));
//    assertEquals("localhost:5000", headers.get("Host"));
//    assertEquals("gzip, deflate, br", headers.get("Accept-Encoding"));
//    assertEquals("keep-alive", headers.get("Connection"));
//  }
//}
