import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;


//Server

public class Driver 
{
static LinkedList<ConnectedClient> theClients = new LinkedList<ConnectedClient>();

public static void main(String[] args) throws Exception
{
//this is creating a socket which is a communication channel between 2 endpoints;
//when we create a socket, we're creating a dedicated connection between 2 points. A server socket knows how to listen,
//and a server socket is required for an initial connection to be created
//listening on port 1234 of this computer
//Needs to be a port > than 1023.
ServerSocket ss = new ServerSocket(1234);
//right now only allow 2 people to connect
while(true)
{
System.out.println("Waiting...");
 
//accept incoming connection
Socket connection = ss.accept();
//created Connected Client object that keeps track of socket and input/output abilities
ConnectedClient cc = new ConnectedClient(connection);
//every time there's a new socket connection, build a new instance of connected client and add that to our global list
Driver.theClients.add(cc);
//Driver.theClients knows about all the clients; new response thread passing it connected client
ResponseThread rt = new ResponseThread(cc);
rt.start();
//saveFile(connection);
System.out.println("Connected");
File myFilesDir = new File("./myFiles");
String[] theFiles = myFilesDir.list();
BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
FileWriter toServer = new FileWriter("./myFiles");
toServer.write("./myFiles/clone" + theFiles[0]);
}
}
//new code
/*
  private static void saveFile(Socket connection) throws Exception 
  {
  File myFilesDir = new File("./myFiles");
       ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
       File myFile = (File)(ois.readObject());
       ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
       Object o = ois.read();
       FileOutputStream fos = new FileOutputStream("./myFiles/clone" + ois);
   

       fos.close();
       fos.flush();
       System.out.println("Writing file complete...");
       String targetFileName = ("./myFiles/clone");
       copyBytes(myFile, targetFileName);
  }
  
  */
  
  
//

private static void copyBytes(File originalFile, String targetFileName) throws Exception 
{

    FileInputStream in = null;
    FileOutputStream out = null;


    in = new FileInputStream(originalFile);
    out = new FileOutputStream(targetFileName);
    int c;

    while ((c = in.read()) != -1) {
        out.write(c);
    }

    out.close();
    in.close();

}//
//end new code
/*
//new code
 
// ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());  
 //       ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());   
  //      byte [] buffer = new byte[1000];  
        File myFilesDir = new File("./myFiles");
        File theFile = new File("./myFiles/" + connection.getInputStream());
        FileInputStream fis = new FileInputStream(theFile);
        File theClone = new File("./myFiles/clone" + connection.getInputStream());
        FileOutputStream fos = new FileOutputStream(theClone);
        fos.write(fis.read());          
        fos.close();  
  
    //    ois.close();  
    //    oos.close();  
 /*
       File myFilesDir = new File("./myFiles");

File theFile = new File("./myFiles/" + connection.getInputStream());
FileInputStream fis = new FileInputStream(theFile);
File theClone = new File("./myFiles/clone");
FileOutputStream fos = new FileOutputStream(theClone);  
fos.write(fis.read());
fos.close();
   
  */
//end new code

}
//new code
 
  
//end new code