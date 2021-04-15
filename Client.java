/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author USER
 */
public class Client {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args []) throws Exception 
    {
        Socket sk=new Socket("127.0.0.1",5000); //membuat socket baru
        BufferedReader sin = new BufferedReader(new InputStreamReader(sk.getInputStream())); // BufferedReader untuk membaca sebuah karakter untuk dijadikan program
        PrintStream sout=new PrintStream(sk.getOutputStream()); 
        BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(true) //apabila sudah ada inputan
        {
             
                System.out.print("Client : "); //program untuk menjalankan client
                s=stdin.readLine();
                sout.println(s);
                
                if(s.equalsIgnoreCase("Bye") )
                {
                    System.out.println("Connection ended by client");
                    break;
                } 
                s=sin.readLine();
                System.out.print("Server : "+s+"\n"); //program sebagai respon dari client
            }
        
            sk.close();
            sin.close();
            sout.close();
            stdin.close();
            
     }
    }
    
    
    

