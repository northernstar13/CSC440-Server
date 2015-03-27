import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class ResponseThread extends Thread

{
ConnectedClient theClient;
//have a ll someplace else with all the clients
LinkedList<ConnectedClient> allTheClients;
//take in a socket, store it as local
//knows about client he's in charge of, and full list of clients
public ResponseThread(ConnectedClient theClient)
{
this.theClient = theClient;
this.allTheClients = Driver.theClients;
}

//automatically gets called when we hit "start" because it extends thread
public void run()
{
//now we want to ask the question... clients prompted with question, then read in the answer
this.theClient.sendMessage("Do you want to share or download?");
String theAnswer = this.theClient.getMessage();
this.theClient.sendMessage(theAnswer);
//start new code
}
}
