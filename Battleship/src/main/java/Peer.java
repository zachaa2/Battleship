package main.java;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;


/**
 * Peer object, whic represents a user connecting to a ServerSocket
 * 
 * @author Aaron Zachariah
 * 
 */
public class Peer {
    
    /**
     * constant for default port
     */
    public static final int DEFAULT_PORT = 8189;
    
    /**
     * Socket object which is created using a server and port value
     */
	private Socket socket;
	
    /**
     * Server address represented as a string value and used to create a socket
     */
    private String server;
    
    /**
     * Port value represented as an integer and used to create a socket
     */
	private int port;
    
    /**
     * Logger object to log and communication between the server and client via the network
     */
    private Logger log;
    
    /**
     * IO objects to communicate with the Server
     */
    private InputStream inStream;
	private OutputStream outStream;
	Scanner in;
	PrintWriter out;
    
    /**
     * Constructor which connects to the server address, using the default port
     * @param server server address represented as a string
     * 
     */
    public Peer(String server) {
        this.server = server;
        this.port = DEFAULT_PORT;
        this.log = Logger.getLogger("global");
    }

    /**
     * Constructor which connects using the server address and port number
     * @param server server address as a string
     * @param p port represented as an integer value
     */
    public Peer(String server, int p) {
        this.server = server;
        this.port = p;
        this.log = Logger.getLogger("global");
    }

    /**
     * Function to attempt to connect to the given address and port number
     * @throws IOException if an IO error occurs when creating the socket
     */
    void connect() throws IOException {
        this.socket = new Socket(this.server, this.port);
        log.info(String.format("Connection to server %s established at port %d.\n", server, port));
        
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
     * method to receive a message from the server
     * @return the message received as a string
     */
    public String receive() {
        String message = this.in.nextLine();
        log.info(String.format("Message %s received", message));
        return message;
    }

    /**
     * getter for the port value
     * @return the port as an int value
     */
    public int getPort() {
        return this.port;
    }
    /**
     * getter for the server address
     * @return the address as a string
     */
    public String getServer() {
        return this.server;
    }

    /**
     * Check if the connection is closed
     * @return true if the socket is closed, false if not
     */
    public boolean isConnectionClosed(){
        return this.socket.isClosed();  
    }


}
