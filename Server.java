/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

/**
 *
 * @author USER
 */
import java.io.IOException; //import kelas-kelas untuk proses input-output
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author USER
 */
public class Server {
    
    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null; //untuk mengatur aliran eksekusi task
    int clientcount=0;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Server serverobj = new Server (5000); //membuat server baru
        serverobj.startServer();
    }
    
    Server(int port){
        this.port = port;
        pool = Executors.newFixedThreadPool(5); //ini menandakan ada 5 thread yang bekerja secara bersamaan di ThreadPool.
        
                
    }
    
    public void startServer() throws IOException {
        
        server = new ServerSocket(5000); //server baru + alamat IPnya
        System.out.println("Server Connected");
        System.out.println("Any client can stop the server by sending -1");
        while(true)
        {
            client = server.accept(); 
            clientcount++;
            ServerThread runnable = new ServerThread(client,clientcount,this);
            pool.execute(runnable);
            
        }
                
    }
    
    private static class ServerThread implements Runnable {  //programServerThread
    
        Server server = null;
        Socket client = null;
        BufferedReader cin;
        PrintStream cout;
        Scanner sc=new Scanner(System.in);
        int id;
        String s;
        
        ServerThread(Socket client, int count, Server server) throws IOException {
        
            this.client = client;
            this.server = server;
            this.id = count;
            System.out.println("Connection "+id+"established with client "+client);
            
            cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout=new PrintStream(client.getOutputStream());
            
    }
        
        @Override  //metode overriding untuk membuat ulang sebuah method pada sub-class.
        public void run() {
            int x=1;
        try{
        while(true){
            s=cin.readLine();
            
                        System.out.print("Client("+id+") : "+s+"\n");
                        System.out.print("Server : ");
                        //s=stdin.readLine();
                            s = sc.nextLine();
                        if (s.equalsIgnoreCase("bye"))
                        {
                            cout.println("BYE");
                            x=0;
                            System.out.println("Connection ended by server");
                            break;
                           
                        }
                        
                        cout.println(s);
                        
        }
      
        cin.close();
        client.close();
        cout.close();
        if(x==0) {
            System.out.println("Server cleaning up.");
            System.exit(0);
        }
    }
     catch (IOException ex) {
            System.err.println("Error : "+ex);
    }
    }
    }
}

