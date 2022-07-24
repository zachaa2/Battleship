package main.java;

import java.io.IOException;
import javax.swing.*;

/**
 * Main class, which is the controller class for this application. Offers p2p support
 * and controls the game logic and game loop.
 * 
 * @author Aaron Zachariah
 * 
 */
public class Main {
    
    /**
     * Global for Player 1 score 
     */
    public static int p1_score = 0;
    
    /**
     * Global for Player 2 score 
     */
    public static int p2_score = 0;
    
    
    
    /**
     * Simple helper function to process a char primitive and turn it into an int
     * Maps chars to int value 0-9 starting from char 'a' to 'j' 
     * @param c char value to process
     * @return integer value of the char, adjusted for the game board
     */
    public static int processChar(char c){ 
        int i = c - 97;
        return i;
    }



    /**
     *  The main function which is the controller of the Application. Will create all necessary obejcts,
     *  and creates all the logic of the game loop
     * @param args command line argument (unused)
     */
    public static void main(String[] args){
        
        String mode = JOptionPane.showInputDialog("Enter \"c\" to connect to game\nEnter \"h\" to host game");
        
        Battleship p1;
        Battleship p2;

        // STARTING THE GAME AS THE SERVER
        if(mode.equals("h")){
            // Start user as server
            Server s = null;
            String info = JOptionPane.showInputDialog("Enter port information (ex: 8189)");
            
            // creating server object
            if(info.length() == 0){
                try {
                    s = new Server();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error Creating Server!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                int port = Server.DEFAULT_PORT;
                try {
                    port = Integer.valueOf(info);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try{
                    s = new Server(port);
                } catch (IOException e){
                    JOptionPane.showMessageDialog(null, "Error Creating Server!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            try {
                s.connect();
            } catch (IOException e2) {
                System.err.println("error connecting with client!");
                System.exit(1);
            }

            // GAME LOOP
            boolean playing = true;
            while(playing){

                // initialize battleship object
                p1 = new Battleship(s);
                GUI gui = new GUI(p1, "Battleship");
                gui.showGUI();
                
                // INITIAL STATE 
                do{
                    gui.setINIT();
                } while (p1.getState() == BattleshipState.INIT);

                // GAMEPLAY STATES
                do{
                    if(p1.getState() == BattleshipState.P1TURN){
                        gui.setP1Turn();
                    } else {
                        // set gui
                        gui.setP2Turn();
                        // wait for coords
                        String coords = p1.recv();
                        // process coords
                        int i = processChar(coords.charAt(0));
                        int j = Character.getNumericValue(coords.charAt(1));
                        // check result of fire
                        boolean r = p1.fireAt(i, j);
                        gui.updateMyBoard(); //update gui
                        // check if gave over
                        boolean over = p1.isOver();
                        if(over){
                            p1.send("game over");
                            p1.setState(BattleshipState.P2WINNER);
                        } else {
                            if(r){
                                p1.send("hit");   
                            } else {
                                p1.send("miss");
                            }
                            p1.setState(BattleshipState.P1TURN);
                        }

                    }
                } while (p1.getState() != BattleshipState.P1WINNER && p1.getState() != BattleshipState.P2WINNER);

                // WINNER STATES
                if(p1.getState() == BattleshipState.P1WINNER){
                    gui.updateScoreBoard(1);
                    gui.setP1Winner();
                } else if (p1.getState() == BattleshipState.P2WINNER) {
                    gui.updateScoreBoard(2);
                    gui.setP2Winner();
                }

                String msg;
                while(true){
                    // ask user to play again
                    msg = JOptionPane.showInputDialog(null, "Would you like to play again? y/n");
                    msg = msg.toLowerCase();
                    // if answer is no, send message to other player, and exit
                    if(msg.equals("n")){
                        p1.send("done");
                        playing = false;
                        break;
                    } 
                    // if answer is yes, check other player's answer
                    else if (msg.equals("y")){
                        p1.send("again");                   // let other peer know to play again
                        String transmission = p1.recv();
                        if(transmission.equals("done")){
                            JOptionPane.showMessageDialog(null, "Other player disconnected...");
                            playing = false;
                            break;
                        } else if (transmission.equals("again")){
                            JOptionPane.showMessageDialog(null, "Restarting Game...");
                            break;
                        } else {
                            System.err.println("invalid msg!");
                            break;
                        }

                    }

                }

                gui.hideGUI();

            }
            
            System.exit(0);

        }
        

        
        // STARTING THE GAME AS THE CLIENT 
        else if (mode.equals("c")) {
            // Start user as client
            // get network info
            String info = JOptionPane.showInputDialog("Enter server and port information (ex: localhost:8189)");
            String[] connection_data = info.split(":", -1);
            
            // create client object
            Peer c = null;
            if(connection_data.length == 1){
                // connecting using default port
                c = new Peer(connection_data[0]);
            } else if (connection_data.length == 2){
                // connecting using user given port
                try{
                    c = new Peer(connection_data[0], Integer.valueOf(connection_data[1])); 
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Incorrect Connection Information", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // error checking
                JOptionPane.showMessageDialog(null, "Incorrect Connection Information", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            // connect to server
            try {
                c.connect();
            } catch (IOException e){
                System.err.println("error connecting to server!");
                System.exit(1);
            }

            boolean playing = true;

            while(playing){

                // initialize battleship object
                p2 = new Battleship(c);
                GUI gui = new GUI(p2, "Battleship");
                gui.showGUI();
                
                // GAME
                // INITIAL STATE
                do{
                    gui.setINIT();
                } while (p2.getState() == BattleshipState.INIT);

                // GAMEPLAY STATES
                do{
                    if(p2.getState() == BattleshipState.P1TURN){
                        // set gui
                        gui.setP1Turn();
                        // wait for fire 
                        String coords = p2.recv();
                        // process coords
                        int i = processChar(coords.charAt(0));
                        int j = Character.getNumericValue(coords.charAt(1));
                        // check result of fire
                        boolean r = p2.fireAt(i, j);
                        gui.updateMyBoard(); //update gui
                        // check if gave over
                        boolean over = p2.isOver(); 
                        if(over){
                            p2.send("game over");
                            p2.setState(BattleshipState.P1WINNER);
                        } else {
                            if(r){
                                p2.send("hit");   
                            } else {
                                p2.send("miss");
                            }
                            p2.setState(BattleshipState.P2TURN);
                        }

                    } else {
                        gui.setP2Turn();
                    }
                } while (p2.getState() != BattleshipState.P1WINNER && p2.getState() != BattleshipState.P2WINNER);

                // WINNER STATES
                if(p2.getState() == BattleshipState.P1WINNER){
                    gui.updateScoreBoard(1);
                    gui.setP1Winner();
                } else if (p2.getState() == BattleshipState.P2WINNER) {
                    gui.updateScoreBoard(2);
                    gui.setP2Winner();
                }

                String msg;
                while(true){
                    // ask user to play again
                    msg = JOptionPane.showInputDialog(null, "Would you like to play again? y/n");
                    msg = msg.toLowerCase();
                    // if answer is no, send message to other player, and exit
                    if(msg.equals("n")){
                        p2.send("done");
                        playing = false;
                        break;
                    }
                    // if answer is yes, check other player's answer
                    else if (msg.equals("y")){
                        p2.send("again");                   // let other peer know to play again
                        String transmission = p2.recv();
                        if(transmission.equals("done")){
                            JOptionPane.showMessageDialog(null, "Other player disconnected...");
                            playing = false;
                            break;
                        } else if (transmission.equals("again")){
                            JOptionPane.showMessageDialog(null, "Restarting Game...");
                            break;
                        } else {
                            System.err.println("invalid msg!");
                            break;
                        }

                    }

                }

                gui.hideGUI();

            }

            System.exit(0);

        } 
        
        

        // OTHER (Error Handling)
        else {   
            System.err.println("Invalid Input!");
            System.exit(0);
        }
        

    }

}

