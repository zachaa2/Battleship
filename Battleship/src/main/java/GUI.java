package main.java;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * The View of the application which controlls how the User visualized the Model, and how the user will
 * interact with the model
 * 
 * @author Aaron Zachariah
 * 
 */
public class GUI extends JFrame {
    
    /**
     * Model object
     */
    Battleship player;
    
    /**
     * Main panel made of 4 subpanels
     */
    JPanel parentPanel = new JPanel(new BorderLayout());
    /**
     * 4 sub panels added in a BorderLayout on the main panel
     * Both right and left panels will be broken into more subpanels which hold the board information
     */
    JPanel headerPanel = new JPanel();
    JPanel footerPanel = new JPanel();
    JPanel leftPanel = new JPanel(new BorderLayout());
    JPanel rightPanel = new JPanel(new BorderLayout());

    /**
     * subpanels for the right panel
     */
    JPanel rightleftPanel = new JPanel();
    JPanel rightrightPanel = new JPanel();
    JPanel righttopPanel = new JPanel();
    JPanel rightbottomPanel = new JPanel();
    JPanel rightcenterPanel = new JPanel(new GridLayout(10, 10));
    /**
     * 2D array of Buttons which represent the player's view of the enemy board
     * These buttons correspond to the enemyBoard field in the Model
     */
    JButton[][] enemyBoardButtons = new JButton[10][10];

    /**
     * subpanels for the left panel
     */
    JPanel leftleftPanel = new JPanel();
    JPanel leftrightPanel = new JPanel();
    JPanel lefttopPanel = new JPanel();
    JPanel leftbottomPanel = new JPanel();
    JPanel leftcenterPanel = new JPanel(new GridLayout(10, 10));
    /**
     * 2D array of buttons for the player's board
     * These buttons correspond to the myBoard field in the Model
     */
    JButton[][] myBoardButtons = new JButton[10][10];

    /**
     * info label to display game state information
     */
    JLabel info = new JLabel();

    /**
     * score label to show the current score
     */
    JLabel score = new JLabel();

    /**
     * left panel labels [header] 
     */                     
    JTextArea lzero = new JTextArea("0"); 
    JTextArea lone = new JTextArea("1");  
    JTextArea ltwo = new JTextArea("2");  
    JTextArea lthree = new JTextArea("3");
    JTextArea lfour = new JTextArea("4"); 
    JTextArea lfive = new JTextArea("5"); 
    JTextArea lsix = new JTextArea("6");  
    JTextArea lseven = new JTextArea("7");
    JTextArea leight = new JTextArea("8");
    JTextArea lnine = new JTextArea("9"); 
    /**
     * right panel labels [header]
     */                    
    JTextArea rzero = new JTextArea("0"); 
    JTextArea rone = new JTextArea("1");  
    JTextArea rtwo = new JTextArea("2");  
    JTextArea rthree = new JTextArea("3");
    JTextArea rfour = new JTextArea("4"); 
    JTextArea rfive = new JTextArea("5"); 
    JTextArea rsix = new JTextArea("6");  
    JTextArea rseven = new JTextArea("7");
    JTextArea reight = new JTextArea("8");
    JTextArea rnine = new JTextArea("9");

    /**
     * left panel labels [margin]
     */                         
    JTextArea la = new JTextArea("    A");  
    JTextArea lb = new JTextArea("    B");  
    JTextArea lc = new JTextArea("    C");  
    JTextArea ld = new JTextArea("    D");  
    JTextArea le = new JTextArea("    E");  
    JTextArea lf = new JTextArea("    F");  
    JTextArea lg = new JTextArea("    G");  
    JTextArea lh = new JTextArea("    H");  
    JTextArea li = new JTextArea("    I");  
    JTextArea lj = new JTextArea("    J");  
    /**
     * right panel labels [margin] 
     */                        
    JTextArea ra = new JTextArea("    A");  
    JTextArea rb = new JTextArea("    B");  
    JTextArea rc = new JTextArea("    C");  
    JTextArea rd = new JTextArea("    D");  
    JTextArea re = new JTextArea("    E");  
    JTextArea rf = new JTextArea("    F");  
    JTextArea rg = new JTextArea("    G");  
    JTextArea rh = new JTextArea("    H");  
    JTextArea ri = new JTextArea("    I");  
    JTextArea rj = new JTextArea("    J");  

    /**
     * View contructor which initializes the window, and creates all components in their initial state
     * @param p Model object
     * @param name String name of window
     */
    public GUI(Battleship p, String name){
        super(name);
        this.player = p;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1200, 800));
        setTitle("Battleship");
        createPanels();
        createBoards();
        createText();
        createToolbar();
        createMenuBar();

    }


    /**
     * Function to set the background color to a given color for all conponents
     * @param c Color object to set the background to
     */
    public void setColor(Color c){

        this.getContentPane().setBackground(c);

        parentPanel.setBackground(c);
        
        headerPanel.setBackground(c);
        footerPanel.setBackground(c);
        leftPanel.setBackground(c);
        rightPanel.setBackground(c);

        rightleftPanel.setBackground(c);
        rightrightPanel.setBackground(c);
        righttopPanel.setBackground(c);
        rightbottomPanel.setBackground(c);
        rightcenterPanel.setBackground(c);

        leftleftPanel.setBackground(c);
        leftrightPanel.setBackground(c);
        lefttopPanel.setBackground(c);
        leftbottomPanel.setBackground(c);
        leftcenterPanel.setBackground(c);

        info.setBackground(c);

        score.setBackground(c);

        // set label colors
        lzero.setBackground(c); 
        lone.setBackground(c);
        ltwo.setBackground(c);
        lthree.setBackground(c);
        lfour.setBackground(c); 
        lfive.setBackground(c);
        lsix.setBackground(c);
        lseven.setBackground(c);
        leight.setBackground(c);
        lnine.setBackground(c);

        rzero.setBackground(c); 
        rone.setBackground(c);
        rtwo.setBackground(c);
        rthree.setBackground(c);
        rfour.setBackground(c);
        rfive.setBackground(c);
        rsix.setBackground(c);
        rseven.setBackground(c);
        reight.setBackground(c);
        rnine.setBackground(c); 

        la.setBackground(c);
        lb.setBackground(c);
        lc.setBackground(c);
        ld.setBackground(c);
        le.setBackground(c);
        lf.setBackground(c);
        lg.setBackground(c);
        lh.setBackground(c);
        li.setBackground(c);
        lj.setBackground(c);

        ra.setBackground(c);
        rb.setBackground(c);
        rc.setBackground(c);
        rd.setBackground(c);
        re.setBackground(c);
        rf.setBackground(c);
        rg.setBackground(c);
        rh.setBackground(c);
        ri.setBackground(c);
        rj.setBackground(c);

    }

    /**
     * Function to initialize the menu bar and its components
     */
    public void createMenuBar(){
        
        JMenuBar menuBar = new JMenuBar();

        // View menu
        JMenu view = new JMenu("View");
        menuBar.add(view);
        // color changer
        JMenuItem item1 = new JMenuItem("Select Background Color");
        item1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                Color c = JColorChooser.showDialog(null, "Please Select a Color", Color.WHITE);
                setColor(c);
            }
        });
        view.add(item1);

        menuBar.revalidate();
        this.setJMenuBar(menuBar);

    }

    /**
     * Function to initialize all the planels and subpanels used in the View. 
     */
    public void createPanels(){
        // add top level panel to frame
        this.add(parentPanel);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
        
        // set sizes
        headerPanel.setPreferredSize(new Dimension(1200, 100));
        footerPanel.setPreferredSize(new Dimension(1200, 100));
        leftPanel.setPreferredSize(new Dimension(600, 300));
        rightPanel.setPreferredSize(new Dimension(600, 300));

        lefttopPanel.setPreferredSize(new Dimension(600, 50));
        leftbottomPanel.setPreferredSize(new Dimension(600, 50));
        leftleftPanel.setPreferredSize(new Dimension(50, 200));
        leftrightPanel.setPreferredSize(new Dimension(50, 200));
        leftcenterPanel.setPreferredSize(new Dimension(500, 200));

        righttopPanel.setPreferredSize(new Dimension(600, 50));
        rightbottomPanel.setPreferredSize(new Dimension(600, 50));
        rightleftPanel.setPreferredSize(new Dimension(50, 200));
        rightrightPanel.setPreferredSize(new Dimension(50, 200));
        rightcenterPanel.setPreferredSize(new Dimension(500, 200));

        // add panels
        leftPanel.add(leftbottomPanel, BorderLayout.SOUTH);
        leftPanel.add(lefttopPanel, BorderLayout.NORTH);
        leftPanel.add(leftleftPanel, BorderLayout.WEST);
        leftPanel.add(leftrightPanel, BorderLayout.EAST);
        leftPanel.add(leftcenterPanel, BorderLayout.CENTER);

        rightPanel.add(rightbottomPanel, BorderLayout.SOUTH);
        rightPanel.add(righttopPanel, BorderLayout.NORTH);
        rightPanel.add(rightleftPanel, BorderLayout.WEST);
        rightPanel.add(rightrightPanel, BorderLayout.EAST);
        rightPanel.add(rightcenterPanel, BorderLayout.CENTER);

        parentPanel.add(headerPanel, BorderLayout.NORTH);
        parentPanel.add(footerPanel, BorderLayout.SOUTH);
        parentPanel.add(leftPanel, BorderLayout.WEST);
        parentPanel.add(rightPanel, BorderLayout.EAST);

    }


    /**
     * Function to create the player and enemy boards
     * Sets the initial contents of the Boards.
     */
    public void createBoards(){

        for(int i = 0; i < 10; i++){
            for(int j = 0;j < 10; j++){
                JButton b = new JButton();   // button to go on MyGrid
                JButton b2 = new JButton();  // button to go on enemy grid
                // myboard buttons
                if(player.myBoard[i][j] == Battleship.NONE){
                    b.setBackground(new Color(51, 153, 255)); // light blue
                } else if (player.myBoard[i][j] == Battleship.MISS){
                    b.setBackground(Color.BLUE);                     // regular blue
                } else if (player.myBoard[i][j] == Battleship.HIT) {
                    b.setBackground(Color.RED);                      // red
                } else {
                    b.setBackground(Color.GRAY);                     // grey
                }
                
                //enemy board buttons
                if(player.enemyBoard[i][j] == Battleship.NONE){
                    b2.setBackground(new Color(51, 153, 255)); // light blue
                } else if (player.myBoard[i][j] == Battleship.MISS){
                    b2.setBackground(Color.BLUE);                     // regular blue
                } else if (player.myBoard[i][j] == Battleship.HIT) {
                    b2.setBackground(Color.RED);                      // red
                } else {
                    b2.setBackground(Color.GRAY);                     // grey
                }
                
                // add buttons
                myBoardButtons[i][j] = b;
                enemyBoardButtons[i][j] = b2;
                leftcenterPanel.add(b);
                rightcenterPanel.add(b2);
            }
        }

    }

    /**
     *  Function to update the player's board with any changes to the field using the 2D array of buttons
     * 
     */
    public void updateMyBoard(){
        
        // update buttons
        for(int i = 0;i < 10;i ++){
            for(int j = 0;j < 10; j++){
                if(player.myBoard[i][j] == Battleship.NONE){
                    myBoardButtons[i][j].setBackground(new Color(51, 153, 255)); // light blue
                } else if (player.myBoard[i][j] == Battleship.MISS){
                    myBoardButtons[i][j].setBackground(Color.BLUE);                     // regular blue
                } else if (player.myBoard[i][j] == Battleship.HIT) {
                    myBoardButtons[i][j].setBackground(Color.RED);                      // red
                } else {
                    myBoardButtons[i][j].setBackground(Color.GRAY);                     // grey
                }
            }
        }
        
        leftcenterPanel.revalidate();
        leftcenterPanel.repaint();
    }
    
    /**
     * Function to update the player's view of the enemy board using the 2D array of buttons
     */
    public void updateEnemyBoard(){
        // update buttons
        for(int i = 0;i < 10;i ++){
            for(int j = 0;j < 10; j++){
                if(player.enemyBoard[i][j] == Battleship.NONE){
                    enemyBoardButtons[i][j].setBackground(new Color(51, 153, 255)); // light blue
                } else if (player.enemyBoard[i][j] == Battleship.MISS){
                    enemyBoardButtons[i][j].setBackground(Color.BLUE);                     // regular blue
                } else if (player.enemyBoard[i][j] == Battleship.HIT) {
                    enemyBoardButtons[i][j].setBackground(Color.RED);                      // red
                } else {
                    enemyBoardButtons[i][j].setBackground(Color.GRAY);                     // grey
                }
            }
        }

        rightcenterPanel.revalidate();
        rightcenterPanel.repaint();
    }



    /**
     * Function to set the text fields on the GUI. All text that does not need to change will be set here
     * This includes the title, and the axis labels for each board
     */
    public void createText(){
        // title
        JLabel heading = new JLabel(String.format("Battleship Game [Player %d]", player.player));
        heading.setFont(new Font("Ariel Black", Font.BOLD, 32));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(heading);
        // initial info text
        this.info.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.info.setText("Configure Board using \"Randomize\". Finish configuration by clicking \"Ready\"");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 14));
        headerPanel.add(this.info);
        // initial score 
        this.score.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.score.setText(String.format("SCORE | PLAYER1: %d | PLAYER2: %d |", Main.p1_score, Main.p2_score));
        this.score.setFont(new Font("Ariel Black", Font.BOLD, 14));
        headerPanel.add(this.score);
        // left panel fonts
        lzero.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lone.setFont(new Font("Ariel Black", Font.BOLD, 14));
        ltwo.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lthree.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lfour.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lfive.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lsix.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lseven.setFont(new Font("Ariel Black", Font.BOLD, 14));
        leight.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lnine.setFont(new Font("Ariel Black", Font.BOLD, 14));
        // right panel fonts
        rzero.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rone.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rtwo.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rthree.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rfour.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rfive.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rsix.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rseven.setFont(new Font("Ariel Black", Font.BOLD, 14));
        reight.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rnine.setFont(new Font("Ariel Black", Font.BOLD, 14));
        // add to left panel
        lefttopPanel.add(Box.createRigidArea(new Dimension(600, 20)));
        lefttopPanel.add(lzero);    lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lone);     lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(ltwo);     lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lthree);   lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lfour);    lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lfive);    lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lsix);     lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lseven);   lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(leight);   lefttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        lefttopPanel.add(lnine);    
        //add to right panel
        righttopPanel.add(Box.createRigidArea(new Dimension(600, 20)));
        righttopPanel.add(Box.createRigidArea(new Dimension(25, 25)));
        righttopPanel.add(rzero);   righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rone);    righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rtwo);    righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rthree);  righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rfour);   righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rfive);   righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rsix);    righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rseven);  righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(reight);  righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        righttopPanel.add(rnine);   righttopPanel.add(Box.createRigidArea(new Dimension(33, 25)));
        // left panel fonts
        la.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lb.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lc.setFont(new Font("Ariel Black", Font.BOLD, 14));
        ld.setFont(new Font("Ariel Black", Font.BOLD, 14));
        le.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lf.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lg.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lh.setFont(new Font("Ariel Black", Font.BOLD, 14));
        li.setFont(new Font("Ariel Black", Font.BOLD, 14));
        lj.setFont(new Font("Ariel Black", Font.BOLD, 14));
        // right panel fonts
        ra.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rb.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rc.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rd.setFont(new Font("Ariel Black", Font.BOLD, 14));
        re.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rf.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rg.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rh.setFont(new Font("Ariel Black", Font.BOLD, 14));
        ri.setFont(new Font("Ariel Black", Font.BOLD, 14));
        rj.setFont(new Font("Ariel Black", Font.BOLD, 14));
        // add to left panel
        leftleftPanel.add(Box.createRigidArea(new Dimension(50, 6)));
        leftleftPanel.add(la);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 16)));
        leftleftPanel.add(lb);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 15)));
        leftleftPanel.add(lc);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 15)));
        leftleftPanel.add(ld);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 14)));
        leftleftPanel.add(le);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        leftleftPanel.add(lf);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        leftleftPanel.add(lg);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        leftleftPanel.add(lh);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        leftleftPanel.add(li);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 12)));
        leftleftPanel.add(lj);      leftleftPanel.add(Box.createRigidArea(new Dimension(50, 12)));
        // add to right panel
        rightleftPanel.add(Box.createRigidArea(new Dimension(50, 6)));
        rightleftPanel.add(ra);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 16)));
        rightleftPanel.add(rb);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 15)));
        rightleftPanel.add(rc);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 15)));
        rightleftPanel.add(rd);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 14)));
        rightleftPanel.add(re);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        rightleftPanel.add(rf);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        rightleftPanel.add(rg);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        rightleftPanel.add(rh);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 13)));
        rightleftPanel.add(ri);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 12)));
        rightleftPanel.add(rj);     rightleftPanel.add(Box.createRigidArea(new Dimension(50, 12)));

    }

    /**
     * function to initialize the toolbar and all the buttons and their functions
     */
    public void createToolbar(){

        JToolBar bar = new JToolBar();
        
        // button to ready up
        JButton ready = new JButton("Ready");
        ready.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // only function if in the INIT state
                if(player.getState() == BattleshipState.INIT){
                    // send ready message and wait for one to come in
                    String msg;
                    do{
                        player.send("ready");
                        msg = player.recv();
                    } while(!msg.equals("ready"));
                    // change state
                    player.setState(BattleshipState.P1TURN);
                }
            }
        });

        // button to re-randomize the board
        JButton randomize = new JButton("Randomize");
        randomize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(player.getState() == BattleshipState.INIT){
                    player.randomize();
                    updateMyBoard();
                }
            }
        });
        
        // button to enter coords to fire at
        JButton fire = new JButton("Fire");
        fire.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // only allow to fire if it is the player's turn
                if( (player.player == 1 && player.getState() == BattleshipState.P1TURN)
                ||  (player.player == 2 && player.getState() == BattleshipState.P2TURN) ){

                    String rows = "abcdefghij";
                    // get input
                    String coords = JOptionPane.showInputDialog("Enter the Coordinate to fire at (ex: \"B7\")");
                    // get coords as integers
                    int i = (coords.toLowerCase()).charAt(0) - 97;
                    int j = Character.getNumericValue(coords.charAt(1));
                    
                    // input validation
                    //check if the length of input is 2
                    if(coords.length() != 2){
                        JOptionPane.showMessageDialog(null, "Input not the correct length!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // parse the second element as number
                    int n;
                    try{
                        n = Integer.parseInt(String.valueOf(coords.charAt(1)));
                    } catch (NumberFormatException e2){
                        JOptionPane.showMessageDialog(null, "Horizontal coordinate must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // check if the first element is an acceptable letter 
                    String r = (String.valueOf(coords.charAt(0))).toLowerCase();
                    if(!rows.contains(r)){
                        JOptionPane.showMessageDialog(null, "Vertical Coordinate must be a letter [A-J]!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // if input is fine, send the coordinates to the other player
                    String msg = r + String.valueOf(n);
                    String transmission;
                    // send the coordinates
                    player.send(msg);
                    // wait for the result (if the game ended or not)
                    transmission = player.recv();
                    // process transmission
                    if(transmission.equals("game over")){
                        player.fireResult(i, j, Battleship.HIT);
                        updateEnemyBoard();
                        // switch state
                        if(player.player == 1){
                            player.setState(BattleshipState.P1WINNER);
                        } else {
                            player.setState(BattleshipState.P2WINNER);
                        }
                    } else if (transmission.equals("hit")){
                        player.fireResult(i, j, Battleship.HIT);
                        updateEnemyBoard();
                        // switch state based on what player it is
                        if(player.player == 1 && player.getState() == BattleshipState.P1TURN){
                            player.setState(BattleshipState.P2TURN);
                        } else if (player.player == 2 && player.getState() == BattleshipState.P2TURN){
                            player.setState(BattleshipState.P1TURN);
                        }
                    } else if (transmission.equals("miss")) {
                        player.fireResult(i, j, Battleship.MISS);
                        updateEnemyBoard();
                        // switch state based on what player it is
                        if(player.player == 1 && player.getState() == BattleshipState.P1TURN){
                            player.setState(BattleshipState.P2TURN);
                        } else if (player.player == 2 && player.getState() == BattleshipState.P2TURN){
                            player.setState(BattleshipState.P1TURN);
                        }
                    } else {
                        System.err.println("invalid result from firing!");
                        // switch state based on what player it is
                        if(player.player == 1 && player.getState() == BattleshipState.P1TURN){
                            player.setState(BattleshipState.P2TURN);
                        } else if (player.player == 2 && player.getState() == BattleshipState.P2TURN){
                            player.setState(BattleshipState.P1TURN);
                        }
                    }
                }
            }
        });

        // Button to open the key for the board
        JButton key = new JButton("Key");
        key.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Key k = new Key("Key");
                k.showKey();
            }
        });

        bar.add(ready);
        bar.add(randomize);
        bar.add(fire);
        bar.add(key);
        bar.setFloatable(false);
        bar.setRollover(true);

        footerPanel.add(bar);

    }
    
    /**
     * Function to update the scoureboard and give a point to the specified player
     * @param player integer value of 1 or 2, depending on who had won the game
     */
    public void updateScoreBoard(int player){

        if(player == 1){
            Main.p1_score++;
        } else {
            Main.p2_score++;
        }
        this.score.setText(String.format("SCORE | PLAYER1: %d | PLAYER2: %d |", Main.p1_score, Main.p2_score));
        this.score.setFont(new Font("Ariel Black", Font.BOLD, 14));

    }
    
    /**
     * Set the info label for the Configure Stage
     */
    public void setINIT(){
        this.info.setText("Configure Board using \"Randomize\". Finish configuration by clicking \"Ready\"");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 18));
        
    }

    /**
     * Set the info label for Player1's turn state
     */
    public void setP1Turn(){
        this.info.setText("Player 1\'s turn. Click \"Fire\" and enter the coordinates");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 22));
    }

    /**
     * Set the info label for the Player2's turn state
     */
    public void setP2Turn(){
        this.info.setText("Player 2\'s turn. Click \"Fire\" and enter the coordinates");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 22));
    }

    /**
     * Set the info label when player1 wins
     */
    public void setP1Winner(){
        this.info.setText("Player 1 Wins!");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 22));
    }

    /**
     * Set the info label when player2 wins
     */
    public void setP2Winner(){
        this.info.setText("Player 2 Wins!");
        this.info.setFont(new Font("Ariel Black", Font.BOLD, 22));
    }

    /**
     * function to make the View visible to the user
     */
    public void showGUI(){

        setVisible(true);

    }

    /**
     * Function to remove the View from the User's screen
     */
    public void hideGUI(){

        setVisible(false);
        // this.dispose();
    }




}


/**
 * Seperate class for the key. The Key shows the color coding for each tile
 * so that the user knows what the colors on the board represent.
 */
class Key extends JFrame {

    /**
     * Single panel to hold the contents of the Frame
     */
    JPanel panel = new JPanel();
    
    /**
     * Basic constructor which initializes the key window
     * @param name string representing the name of the frame
     */
    public Key(String name){
        
        super(name);
        setResizable(false);
        setTitle("Key");
        setSize(250, 300);
        this.add(panel);
        createKey();
    }
    
    /**
     * Function to create all the contents regarding the key and place them on the panel
     */
    public void createKey(){

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(30, 30)));
        // untouched tile
        JButton b1 = new JButton(); 
        b1.setBackground(new Color(51, 153, 255)); 
        b1.setPreferredSize(new Dimension(40,30));
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(b1);
        JTextArea l1 = new JTextArea("                         Untouched Tile");
        l1.setFont(new Font("Ariel Black", Font.BOLD, 12));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(l1);
        
        // miss tile
        JButton b2 = new JButton();
        b2.setBackground(Color.BLUE);
        b2.setPreferredSize(new Dimension(40, 30));
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(b2);
        JTextArea t2 = new JTextArea("\t     Miss");
        t2.setFont(new Font("Ariel Black", Font.BOLD, 12));
        t2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(t2);

        // hit tile
        JButton b3 = new JButton();
        b3.setBackground(Color.RED);
        b3.setPreferredSize(new Dimension(40, 30));
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(b3);
        JTextArea t3 = new JTextArea("\t       Hit");
        t3.setFont(new Font("Ariel Black", Font.BOLD, 12));
        t3.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(t3);
        // ship tile
        JButton b4 = new JButton();
        b4.setBackground(Color.GRAY);
        b4.setPreferredSize(new Dimension(40, 30));
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(b4);
        JTextArea t4 = new JTextArea("\t     Ship");
        t4.setFont(new Font("Ariel Black", Font.BOLD, 12));
        t4.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(t4);
    }

    /**
     * Function to make the key window visible to the user
     */
    public void showKey(){
        setVisible(true);
    }

}