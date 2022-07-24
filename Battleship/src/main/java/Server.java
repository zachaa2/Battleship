package main.java;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

/**
 * Server object, which will open a ServerSocket and allow a client to connect
 * 
 * @author Aaron Zachariah
 * 
 */
public class Server {
    
    /**
     * Main ServerSocket object to accept a connection from a client
     */
    ServerSocket serverSocket;
    
    /**
     * The socket which is connecting to the established ServerSocket
     */
    Socket socket;

    /**
     * Port value of the server socket
     */
    int port;

    /**
     * Log object to log any communication between the server/client
     */
    Logger log;

    /**
     * IO object to facilitate communication between the server/client
     */
    private InputStream inStream;
	private OutputStream outStream;
	Scanner in;
	PrintWriter out;
    
    /**
     * Default port constant
     */
    public static final int DEFAULT_PORT = 8189;

    /**
     * Constructor which opens a ServerSocket on the default port
     * @throws IOException if an error occurs when opening the socket
     */
    public Server() throws IOException {
        this.log = Logger.getLogger("global");
        this.port = DEFAULT_PORT;
        this.serverSocket = new ServerSocket(this.port);
        log.info(String.format("ServerSocket created on port %d.\n", this.port));
    }

    /**
     * Constructor which opens a ServerSocket on the specified port
     * @param p port value to open socket with
     * @throws IOException if an error occurs when opening the socket
     */
    public Server(int p) throws IOException {
        this.log = Logger.getLogger("global");
        this.port = p;
        this.serverSocket = new ServerSocket(Integer.valueOf(port));
        log.info(String.format("ServerSocket created on port %d.\n", this.port));
    }

    /**
     * Attempt to connect to a client by accepting any connection to the ServerSocket
     * @throws IOException if there was an error accepting the connection, or creating the stream objects
     */
    public void connect() throws IOException {
        this.socket = this.serverSocket.accept();
        log.info(String.format("Incoming connection from a client at %s accepted.\n", this.socket.getRemoteSocketAddress().toString()));
        this.inStream =  this.socket.getInputStream();
		this.outStream = this.socket.getOutputStream();
		this.in = new Scanner(this.inStream);
		this.out = new PrintWriter(new OutputStreamWriter(this.outStream, StandardCharsets.UTF_8), true /*autoFlush */);
    }

    /**
     * Method to send a message across the network
     * @param message message to be sent, represented as a string
     */
    public void send(String message){
        this.out.println(message);
        log.info(String.format("Sent message %s", message));
    }

    /**
     * method to receive a message from the client
     * @return the message received as a string
     */
    public String receive(){
        String message = this.in.nextLine();
        log.info(String.format("Message %s received.\n", message));
		return message;
    }
    
    /**
     * getter for the port value
     * @return the port as an int value
     */
    public int getPort(){
        return this.port;
    }


}
