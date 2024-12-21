import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args){

    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 9092;
    //
    
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      clientSocket = serverSocket.accept();

      DataInputStream in = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

      int messageSize = 5;
      int header = 7;

      String clientInput = new String(in.readAllBytes(), StandardCharsets.UTF_8);
      System.out.println(clientInput);

      out.writeInt(messageSize);
      out.writeInt(header);

    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }
}
