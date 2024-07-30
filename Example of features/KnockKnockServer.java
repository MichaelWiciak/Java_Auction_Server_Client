
import java.net.*;
import java.io.*;

public class KnockKnockServer {

    private ServerSocket serverSocket = null;
    private KnockKnockProtocol kkp = null;

    public KnockKnockServer() {
        try {
            serverSocket = new ServerSocket(2323);
        }
        catch (IOException e) {
            System.err.println("Could not listen on port: 2323.");
            System.exit(1);
        }
        kkp = new KnockKnockProtocol();
    }

    public void runServer() {

        Socket clientSocket = null;

        while( true ){

            try {
   	            clientSocket = serverSocket.accept();
            }
            catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            System.out.println("clientSocket port: " + clientSocket.getPort() );
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
    	    	                		new InputStreamReader(
    	                        			clientSocket.getInputStream()));
                String inputLine, outputLine;

                outputLine = kkp.processInput(null);
                out.println(outputLine);

                while ((inputLine = in.readLine()) != null) {
                     outputLine = kkp.processInput(inputLine);
                     out.println(outputLine);
                     if (outputLine.equals("Bye."))
                        break;
                }
                out.close();
                in.close();
                clientSocket.close();
            }
            catch (IOException e) {
                System.out.println( e );
            }
        }
    }

    public static void main( String[] args ) {
      KnockKnockServer kks = new KnockKnockServer();
      kks.runServer();
    }
}

