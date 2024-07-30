

import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(2323);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 2323.");
            System.exit(-1);
        }

        while (listening)
	      new KKClientHandler(serverSocket.accept()).start();

        serverSocket.close();
    }
}
