
import java.net.*;
import java.io.*;
import java.util.*;

public class KKClientHandler extends Thread {
    private Socket socket = null;

    public KKClientHandler(Socket socket) {
		super("KKClientHandler");
		this.socket = socket;
    }

    public void run() {

		try {

			// Input and output streams to/from the client.
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
						new InputStreamReader(
						socket.getInputStream()));

			// Logging.
			InetAddress inet = socket.getInetAddress();
			Date date = new Date();
			System.out.println("\nDate " + date.toString() );
			System.out.println("Connection made from " + inet.getHostName() );

			// Initialise a protocol object for this client.
			String inputLine, outputLine;
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);

			// Sequential protocol.
			while( (inputLine = in.readLine())!=null ) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}

			// Free up resources for this connection.
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
