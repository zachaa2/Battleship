package main.java;

import java.util.*;


/**
 * The Model of the application, where each object is a battleship board, represented by a 2D array.
 * 
 * @author Aaron Zachariah
 * 
 */
public class Battleship {

    // constants for the game board
    public static final int NONE = 0;
    public static final int HIT = 1;
    public static final int MISS = 2;
    public static final int SHIP = 3;


    /**
     * Client/Server objects, depending on the player.
     * If the player is the host, they are the server.
     * If the player is the connector, they are the peer.
     */
    public Peer p = null;
    public Server s = null;

    /**
     * Player board represented as 2D array of constants
     */
    public int[][] myBoard = new int[10][10];
    
    /**
     * Opponent board represented as 2D array of constants
     */    
    public int[][] enemyBoard = new int[10][10];

    /**
     * State object to control what state the Model is in. This state value helps to control
     * the look and functionality of the view, and well as the turn based logic of the controller 
     */
    public BattleshipState state = BattleshipState.INIT;
    /**
     * integer representation of the player
     * if player is 1, player is the host
     * if player is 2, player is the peer
     */
    public int player;

    /**
     * Constructor for the player2 (the player connecting to a host)
     * @param peer peer object which communicated with the server
     */
    public Battleship(Peer peer){
        this.p = peer;
        this.player = 2;

        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                // initialize board
                enemyBoard[i][j] = 0;
                myBoard[i][j] = 0;
            }
        }
        // randomize player's board to random ships placement
        randomize();

    }

    /**
     * Constructor for the player1 (the host)
     * @param serv server object which communicated with the peer
     */
    public Battleship(Server serv){
        this.s = serv;
        this.player = 1;

        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                // initialize board
                enemyBoard[i][j] = Battleship.NONE;
                myBoard[i][j] = Battleship.NONE;
            }
        }
        // randomize player's board to random ships placement
        randomize();

    }

    /**
     * Place a random ship of length 4 on myBoard
     * @param r RNG to add randomness in ship placement 
     */
    public void placeShip4(Random r){
        boolean safe = true;
        boolean placed = false;
        while(!placed){
            safe = true;
            // get half
            int half = r.nextInt(2);
            if(half == 0){
                // pick a starting pos
                int i = r.nextInt(5);
                int j = r.nextInt(7);
                // check if pos is empty
                for(int k = 0; k < 4; k ++){
                    if (myBoard[i][j+k] == Battleship.SHIP){
                        safe = false;
                    }
                }
                if(safe == false){
                    continue;
                } else {
                    // place ship
                    for(int k = 0; k < 4; k++){
                        myBoard[i][j+k] = Battleship.SHIP;
                    }
                    placed = true;
                }    
            } else {
                int i = r.nextInt(5);
                int j = r.nextInt(7);
                i = i + 5;
                for(int k = 0; k < 4; k ++){
                    if (myBoard[i][j+k] == Battleship.SHIP){
                        safe = false;
                    }
                }
                if(safe == false){
                    continue;
                } else {
                    // place ship
                    for(int k = 0; k < 4; k++){
                        myBoard[i][j+k] = Battleship.SHIP;
                    }
                    placed = true;
                }
            }
        } 
    }

    /**
     * Place a random ship of length 3 on myBoard
     * @param r RNG to add randomness in ship placement 
     */
    public void placeShip3(Random r){
        // Place 3 length ship
        boolean safe = true;
        boolean placed = false;
        while(!placed){
            safe = true;
            // get half
            int half = r.nextInt(2);
            if(half == 0){
                // get starting pos
                int i = r.nextInt(7);
                int j = r.nextInt(5);
                // check if pos is empty
                for(int k = 0; k < 3; k ++){
                    if (myBoard[i+k][j] == Battleship.SHIP){
                        safe = false;
                    }
                }
                if(safe == false){
                    continue;
                } else {
                    // place ship
                    for(int k = 0; k < 3; k++){
                        myBoard[i+k][j] = Battleship.SHIP;
                    }
                    placed = true;
                }
            } else {
                // get starting pos
                int i = r.nextInt(7);
                int j = r.nextInt(5);
                j = j + 5;
                // check if pos is empty
                for(int k = 0; k < 3; k ++){
                    if (myBoard[i+k][j] == Battleship.SHIP){
                        safe = false;
                    }
                }
                if(safe == false){
                    continue;
                } else {
                    // place ship
                    for(int k = 0; k < 3; k++){
                        myBoard[i+k][j] = Battleship.SHIP;
                    }
                    placed = true;
                }
            }
        }
    }


    /**
     * Place a random ship of length 2 on myBoard
     * @param r RNG to add randomness in ship placement 
     */
    public void placeShip2(Random r){
        
        boolean placed = false;
        while(!placed){
            // get starting pos
            int i = r.nextInt(8);
            i = i + 1;
            int j = r.nextInt(8);
            j = j + 1;
            int dir = r.nextInt(4);
            // up
            if(dir == 0){
                // check
                if(myBoard[i][j] == Battleship.SHIP || myBoard[i-1][j] == Battleship.SHIP){
                    continue;
                } else {
                    // place ship
                    myBoard[i][j] = Battleship.SHIP; myBoard[i-1][j] = Battleship.SHIP;
                    placed = true;
                }
            } 
            // left
            else if (dir == 1) {
                // check
                if (myBoard[i][j] == Battleship.SHIP || myBoard[i][j-1] == Battleship.SHIP){
                    continue;
                } else {
                    // place ship
                    myBoard[i][j] = Battleship.SHIP; myBoard[i][j-1] = Battleship.SHIP;
                    placed = true;
                }
            }
            // right
            else if (dir == 2) {
                // check
                if (myBoard[i][j] == Battleship.SHIP || myBoard[i][j+1] == Battleship.SHIP){
                    continue;
                } else {
                    // place ship
                    myBoard[i][j] = Battleship.SHIP; myBoard[i][j+1] = Battleship.SHIP;
                    placed = true;
                }
            }
            // down
            else if (dir == 3) {
                // check
                if (myBoard[i][j] == Battleship.SHIP || myBoard[i+1][j] == Battleship.SHIP){
                    continue;
                } else {
                    // place ship
                    myBoard[i][j] = Battleship.SHIP; myBoard[i+1][j-1] = Battleship.SHIP;
                    placed = true;
                }
            }
        }
    }

    /**
     * Place a random ship of length 1 on myBoard
     * @param r RNG to add randomness in ship placement 
     */
    public void placeShip1(Random r){
        boolean placed = false;
        
        while(!placed){
            int i = r.nextInt(10);
            int j = r.nextInt(10);
            // check space
            if(myBoard[i][j] == Battleship.SHIP){
                continue;
            } else {
                myBoard[i][j] = Battleship.SHIP;
                placed = true;
            }
        }
    }


    /**
     * Function to randomize the ship placements using the myBoard object
     * Ships of length 4, 3, 2, and 1 will be randomly spread through the 
     * 10x10 board
     */
    public void randomize(){

        for(int i = 0;i < 10; i++){
            for(int j = 0;j < 10; j++){
                myBoard[i][j] = Battleship.NONE;
            }
        }

        Random r = new Random();
        
        // Place the 4 length ship
        placeShip4(r);

        // place 3 length ship
        placeShip3(r);
        placeShip3(r);
        
        // place 2 length ships
        placeShip2(r);
        placeShip2(r);
        placeShip2(r);

        // place 1 length ship
        placeShip1(r);
        placeShip1(r);

    }
    
    /**
     * Function to facilitate an enemy firing at the player's board
     * @param i row index of the 2D array
     * @param j column index of the 2D array
     * @return boolean value based on if a ship was hit
     */
    public boolean fireAt(int i, int j){
        if(myBoard[i][j] == Battleship.NONE){
            myBoard[i][j] = Battleship.MISS;
            return false;
        } else if(myBoard[i][j] == Battleship.SHIP){
            myBoard[i][j] = Battleship.HIT;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Function to update the current player's view of the enemy board, based on the result of their guess.
     * The result value will be stored in the position (i, j)
     * @param i row index of the 2D array
     * @param j column index of the 2D array
     * @param result integer constant of the result of the fire function
     */
    public void fireResult(int i, int j, int result){
        this.enemyBoard[i][j] = result;
    }
    /**
     * Check if the game s over by passing through the player's board
     * and checking how many unmarked ship are left
     * @return boolean value. true if the game is over, and false if not
     */
    public boolean isOver(){
        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j<10;j++){
                if(myBoard[i][j] == Battleship.SHIP){
                    ctr ++;
                }
            }
        }
        if(ctr == 0){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Wrapper function for the send method within the Peer/Server object
     * @param msg message to send over the network
     */
    public void send(String msg){
        if(this.p != null){
            this.p.send(msg);
            
        } else {
            this.s.send(msg);
            
        }
    }
    
    /**
     * Wrapper for the receive method in the Peer/Server object
     * @return the message received as a string
     */
    public String recv(){
        String transmission; 
        if(this.p != null){
            transmission = this.p.receive();
        } else {
            transmission = this.s.receive();
        }
        return transmission;
    }
    
    /**
     * Print method to print the player's board
     * Key:
     * S - Ship
     * X - Hit Ship
     * W - Water
     * M - Miss
     * E - Error (invalid data)
     */
    public void printMyBoard() {
        for(int i =0;i < 10; i++){
            for(int j =0;j < 10;j++){
                if(myBoard[i][j] == Battleship.SHIP){
                    System.out.print("S");
                } else if (myBoard[i][j] == Battleship.HIT){
                    System.out.print("X");
                } else if (myBoard[i][j] == Battleship.NONE) {
                    System.out.print("W");
                } else if (myBoard[i][j] == Battleship.MISS) {
                    System.out.print("M");
                } else {
                    System.out.print("E");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Getter for current state
     * @return the current state of the battleship object
     */
    public BattleshipState getState(){
        return this.state;
    }

    /**
     * Setter for the current state
     * @param s State to set the Battleship model to
     */
    public void setState(BattleshipState s){
        this.state = s; 
    }


}
