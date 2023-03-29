import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

public class Server {
  private static final int port = 8080;

  public static void main(String[] args) {
    var webServer = new WebServer(port);
    var xmlRpcServer = webServer.getXmlRpcServer();
    var handler = new PropertyHandlerMapping();
    try {
      handler.addHandler("MessageService", MessageService.class);
      handler.addHandler("ProcService", ProcService.class);
      xmlRpcServer.setHandlerMapping(handler);
      webServer.start();
      System.out.println("Server started");
    } catch (final Exception e) {
      System.out.println("Something went wrong!");
    }
  }
}
