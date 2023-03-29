public class MessageService {
  private static final int DELAY_SEC = 3;

  public String echo(final String message) {
    System.out.println("Message received: " + message);
    System.out.println("Response. Sending echo message...");
    return "Echo: " + message;
  }

  public String echoWithDelay(final String message) throws InterruptedException {
    System.out.println("Message received: " + message);
    System.out.println(
        String.format("Waiting for %d seconds before sending response...", DELAY_SEC));
    Thread.sleep(DELAY_SEC * 1000);
    System.out.println("Response. Sending echo message...");
    return "Echo after delay: " + message;
  }
}
