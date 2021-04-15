/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoServer;

import java.io.*;

import java.net.*;

/**
 *
 * @author USER
 */
public class EchoServer {   
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
            
        try(ServerSocket echoServer = new ServerSocket(8000)){
            Socket clientSocket= echoServer.accept();
            
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outPut = new PrintWriter(clientSocket.getOutputStream(), true);
            
            String line;
            while ((line = bufferReader.readLine()) != null) {
                System.out.printf("Sent from the client: "+line);
                outPut.println(line);
         }
        }   
            
        catch (IOException ioe) {
            System.err.println(ioe);
        }


    }

}
