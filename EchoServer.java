/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoServer;

import java.io.*; //import yang berisi kelas-kelas untuk proses input-output

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
            
        try(ServerSocket echoServer = new ServerSocket(8000)){ // server utama socket (titik awal komunikasi pada program).
            Socket clientSocket= echoServer.accept(); // untuk membuat socket baru
            
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // untuk membaca sebuah karakter
            PrintWriter outPut = new PrintWriter(clientSocket.getOutputStream(), true); // print output
            
            String line;
            while ((line = bufferReader.readLine()) != null) {  //apabila sudah ada inputan dari client 
                System.out.printf("Sent from the client: "+line);
                outPut.println(line);
         }
        }   
            
        catch (IOException ioe) {
            System.err.println(ioe);
        }


    }

}
