//package server;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import org.junit.After;
//import org.junit.Test;
//
//public class ClientHandlerTest {
//  private SocketIo socketIo;
//  private String consoleOutput;
//  private final String testMsg = "test message";
//  private final String multipleMsgs = "first \n second \n third";
//  private final String secondClientMsg = "hello from second client";
//  private final String nullMsg = "";
//
//  public void initialize(String message) throws IOException {
//    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
//    OutputStream outputStream = new ByteArrayOutputStream();
//
//    ServerSocket serverSocket = mock(ServerSocket.class);
//    Socket clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
//    when(serverSocket.accept()).thenReturn(clientSocket);
//
//    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();
//    TestHelpers.initializeServer(clientSocket);
//
//    consoleOutput = consoleOut.toString();
//  }
//
//  public void initializeWithMockIo(String message) throws IOException {
//    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
//    OutputStream outputStream = new ByteArrayOutputStream();
//
//    ServerSocket serverSocket = mock(ServerSocket.class);
//    Socket clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
//    when(serverSocket.accept()).thenReturn(clientSocket);
//
//    socketIo = mock(SocketIo.class);
//    when(socketIo.read()).thenReturn(message);
//    //when(socketIo.send(message)).thenReturn(message);
//
//    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();
//    TestHelpers.initializeServer(clientSocket);
//
//    consoleOutput = consoleOut.toString();
//  }
//
//  public void initializeWithSecondClient(String message, String message2) throws IOException {
//    InputStream inputStream = new ByteArrayInputStream(message.getBytes());
//    OutputStream outputStream = new ByteArrayOutputStream();
//
//    InputStream inputStream2 = new ByteArrayInputStream(message2.getBytes());
//    OutputStream outputStream2 = new ByteArrayOutputStream();
//
//    ServerSocket serverSocket = mock(ServerSocket.class);
//    Socket clientSocket = TestHelpers.socket(inputStream, outputStream, 90210);
//    Socket clientSocket2 = TestHelpers.socket(inputStream2, outputStream2, 1337);
//    when(serverSocket.accept()).thenReturn(clientSocket, clientSocket2);
//
//    ByteArrayOutputStream consoleOut = TestHelpers.setConsoleOutput();
//    TestHelpers.initializeServer(clientSocket);
//    TestHelpers.initializeServer(clientSocket2);
//
//    consoleOutput = consoleOut.toString();
//  }
//
//  @After
//  public void tearDown() {
//    TestHelpers.restoreInitialStreams();
//  }
//
//  @Test
//  public void connectsToClient() throws IOException {
//    initialize(nullMsg);
//    assertThat(consoleOutput, containsString("now connected"));
//  }
//
//  @Test
//  public void echoesResponseIfMessageReceived() throws IOException {
//    initialize(testMsg);
//    assertThat(consoleOutput, containsString("Message echoed"));
//  }
//
//  @Test
//  public void doesNotEchoIfMessageIsNull() throws IOException {
//    initializeWithMockIo(nullMsg);
//    verify(socketIo, never()).send(null);
//  }
//
//  @Test
//  public void receivesMultipleMessages() throws IOException {
//    initialize(multipleMsgs);
//    assertThat(consoleOutput, containsString("first"));
//    assertThat(consoleOutput, containsString("second"));
//    assertThat(consoleOutput, containsString("third"));
//  }
//
//  @Test
//  public void sendsMultipleEchoes() throws IOException {
//    initialize(multipleMsgs);
//    int echoCount = (consoleOutput.split("Message echoed").length) - 1;
//
//    assertEquals(3, echoCount);
//  }
//
//  @Test
//  public void assignsMultipleClientIds() throws IOException {
//    initializeWithSecondClient(testMsg, secondClientMsg);
//    assertThat(consoleOutput, containsString("EchoClient90210"));
//    assertThat(consoleOutput, containsString("EchoClient1337"));
//  }
//
//  @Test
//  public void connectsToMultipleClients() throws IOException {
//    initializeWithSecondClient(testMsg, secondClientMsg);
//    int connectionCount = (consoleOutput.split("now connected").length) - 1;
//
//    assertEquals(2, connectionCount);
//  }
//
//  @Test
//  public void canReceiveMessagesFromMultipleClients() throws IOException {
//    initializeWithSecondClient(multipleMsgs, secondClientMsg);
//    assertThat(consoleOutput, containsString("first"));
//    assertThat(consoleOutput, containsString("second"));
//    assertThat(consoleOutput, containsString("third"));
//    assertThat(consoleOutput, containsString("hello from second client"));
//  }
//
//  @Test
//  public void canEchoMessagesFromMultipleClients() throws IOException {
//    initializeWithSecondClient(multipleMsgs, secondClientMsg);
//    int echoCount = (consoleOutput.split("Message echoed").length) - 1;
//
//    assertEquals(4, echoCount);
//  }
//
//  @Test
//  public void closesServerConnection() throws IOException {
//    initialize(nullMsg);
//
//    assertThat(consoleOutput, containsString("disconnected"));
//  }
//}
