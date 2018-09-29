/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author RafaelAhmedov
 */
public class TCPClient {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws IOException {
        socketClient("localhost", 6789);
    }

    
    public static void socketClient(String ip,int port) throws IOException{
    Socket clientSocket = new Socket(ip, port);
        DataOutputStream outToStream = new DataOutputStream(clientSocket.getOutputStream());

        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        byte[] bytes = readBytesFromFile(fileName);
        outToStream.write(bytes);
       
        clientSocket.close();
    }
    
    private static byte[] readBytesFromFile(String filePath) {
        byte[] byteArray = null;

        try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
            File file = new File(filePath);
            byteArray = new byte[(int) file.length()];
            fileInputStream.read(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

}
