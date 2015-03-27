import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ConnectedClient 
{
private Socket theSocket;
private PrintWriter output;
private Scanner input;
//connected client will take in the socket and set up the three variables, then set up the way to do input
//the way to output
public ConnectedClient(Socket theSocket)
{
try
{
this.theSocket = theSocket;
this.output = new PrintWriter(this.theSocket.getOutputStream(), true);
this.input = new Scanner(this.theSocket.getInputStream());
}
catch(Exception e)
{
//never happen :) 
}
}
public void sendMessage(String msg)
{
this.output.println(msg);
}
public String getMessage()
{
return this.input.nextLine();
}

}
