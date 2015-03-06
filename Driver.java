import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


//Server

public class Driver 
{
public static void main(String[] args) throws Exception
{
//this is creating a socket which is a communication channel between 2 endpoints;
//when we create a socket, we're creating a dedicated connection between 2 points. A server socket knows how to listen,
//and a server socket is required for an initial connection to be created
//listening on port 1234 of this computer
//Needs to be a port > than 1023.
ServerSocket ss = new ServerSocket(1234);
int count = 0;
while(true)
{
System.out.println("Waiting...");
/*eventually accept incoming connection
*holds an open communication line between this server and the terminal
* */
 
Socket connection = ss.accept();
ResponseThread rt = new ResponseThread(connection);
System.out.println("Connected");
//start spawns it off as thread
rt.start();
}

}
}
