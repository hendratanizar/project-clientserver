/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoServer;

import java.net.*;
import java.io.*;
import java.util.Scanner; //import scanner
/**
 *
 * @author USER
 */
public class EchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        InetAddress localHost = InetAddress.getLocalHost(); //untuk mengambil alamat IP
        Socket socket = new Socket(localHost,8000); //membuat socket baru
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //print output
        BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream())); //untuk membaca sebuah karakter untuk dijadikan program
        
        System.out.println("Telah terkoneksi dengan server");
        Scanner scanner = new Scanner(System.in); //untuk mendeteksi inputan
        while(true){ //apabila sudah terkoneksi
            System.out.println("Tulis kalimat :");
            String input = scanner.nextLine();
            if("exit".equalsIgnoreCase(input)) {
                break;
                
            }
            out.println(input);
            String response = buffer.readLine();
            System.out.println("Respon : "+response);
            
        }
        
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
