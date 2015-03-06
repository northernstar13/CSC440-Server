import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ResponseThread extends Thread

{
private Socket s;
private String hello = "<html><body><h1>Hello World</h1><img src=spongeBob.jpg/></body></html>";
private String index = "<html><body><h1>Homepage</h1></body></html>";
private String error = "<html><body><h1>Page Not Found</h1></body></html>";
public ResponseThread(Socket s)
{
this.s = s;
}
public void run()
{
try
{
System.out.println("Enter a command: ");
//create a scanner for the command from the client
Scanner input = new Scanner(this.s.getInputStream());
//capture the client's request in a string
String req = input.nextLine();
//show that the client has an incoming request
System.out.println("Command from client: " + req);
//create a PrintWriter for the output echo
PrintWriter output = new PrintWriter(this.s.getOutputStream());
//new string that will be built up with our "echo"
String echo = "";
for (int i = 0; i < req.length(); i++) 
{
//build up the echo by running through the input stream and capturing it in our string
            echo = echo + req.charAt(i);
            
        }
//show in terminal (client) that there is in fact an echo
output.println("Echo from server: " + echo);
//show in server that there is in fact an echo
System.out.println("Echo to client: " + echo);


output.flush();
output.close();
/* for(int i = 0; i < 1000000; i ++)
{
System.out.println("Wasting Time");
}*/
} 
catch(Exception e)
{
}
}
}

