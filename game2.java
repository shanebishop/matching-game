/*
 * Programmer:   Shane Bishop
 * Date:         October 26, 2015
 * Program Name: Matching Game
 * 
 * Program Description:
 * This program uses arrays to simulate a matching game. A grid of cards will 
 * be displayed and the user is to select two cards at a time looking for 
 * matching cards. The goal of the game is to see how quickly the user can find 
 * all the matches.
 */
package matchinggamejframe;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class game2 extends JFrame {
    JButton[] arr_cardButtons = new JButton[16]; // Creates 16 card JButtons
    
    // Creates the JButtons required for main program functionality
    JButton playButton = new JButton(); // Creates new JButton playButton
    JButton exitButton = new JButton(); // Creates new JButton exitButton
    
    JButton lastButtonClicked = null; // Declares a new JButton lastButtonClicked
    
    // Creates new JLabels
    JLabel instructions1Label = new JLabel(); // Creates new JLabel instructions1Label
    JLabel instructions2Label = new JLabel(); // Creates new JLabel instructions2Label
    
    // Sets up ArrayLists
    ArrayList <String> arl_cards = new ArrayList(); // Holds cards in original list
    ArrayList <String> arl_set = new ArrayList();   // Holds cards set at random
    
    // Sets up ImageIcons to hold and change pictures for matching
    ImageIcon ii_a = new ImageIcon("ram.jpg");       // Sets "ii_a" to ram.jpg
    ImageIcon ii_b = new ImageIcon("case.jpg");      // Sets "ii_b" to case.jpg
    ImageIcon ii_c = new ImageIcon("dvd.jpg");       // Sets "ii_c" to dvd.jpg
    ImageIcon ii_d = new ImageIcon("harddrive.jpg"); // And so on ...
    ImageIcon ii_e = new ImageIcon("keyboard.jpg");
    ImageIcon ii_f = new ImageIcon("mice.jpg");
    ImageIcon ii_g = new ImageIcon("monitor.jpg");
    ImageIcon ii_h = new ImageIcon("printer.jpg");
    ImageIcon ii_back = new ImageIcon("cardback.jpg");
    ImageIcon ii_done = new ImageIcon("done.jpg");
    
    // Sets up other field variables
    int int_c1, int_c2, int_card1a, int_card2a; // Declares some field ints
    int int_cardsClicked = 0;        // Initializes int_cardsClicked to 0
    int int_cardsleft = 16;          // Initializes int_cardsleft to 16
    int int_timeToWait = 10000;      // Makes the program wait 10 seconds
    int[] arr_change = new int [16]; // Creates an int array with 16 indices
    public Container container;      // Declares Container container to hold JComponents
    
    /**
     * Constructor to set up the GUI
     */
    public game2() {
        super("Matching Game");                         // Sets the title of the JFrame to "Matching Game"
        container = this.getContentPane();              // Initializes container as the current content pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handles the CLOSE button
        this.container.setLayout(null);                 // Sets container's layout to null
        
        // Sets the JFrame to dimensions 400 by 500 pixels
        this.getContentPane().setMinimumSize(new Dimension(400, 500));
        this.getContentPane().setMaximumSize(new Dimension(400, 500));
        this.getContentPane().setPreferredSize(new Dimension(400, 500));
        
        // Initializes arr_cardButtons
        for (int int_i = 0; int_i < arr_cardButtons.length; int_i++) {
            arr_cardButtons[int_i] = new JButton();  // Initializes each element of arr_cardButtons to a new JButton
            arr_cardButtons[int_i].setIcon(ii_back); // Sets each button to display ii_back
            container.add(arr_cardButtons[int_i]);   // Adds each button to the content pane
        }
        
        // Sets the positions of the card JButtons in row 1
        arr_cardButtons[0].setBounds(25, 5, 75, 75);
        arr_cardButtons[1].setBounds(115, 5, 75, 75);
        arr_cardButtons[2].setBounds(205, 5, 75, 75);
        arr_cardButtons[3].setBounds(295, 5, 75, 75);
        
        // Sets the positions of the card JButtons in row 2
        arr_cardButtons[4].setBounds(25, 90, 75, 75);
        arr_cardButtons[5].setBounds(115, 90, 75, 75);
        arr_cardButtons[6].setBounds(205, 90, 75, 75);
        arr_cardButtons[7].setBounds(295, 90, 75, 75);
        
        // Sets the positions of the card JButtons in row 3
        arr_cardButtons[8].setBounds(25, 180, 75, 75);
        arr_cardButtons[9].setBounds(115, 180, 75, 75);
        arr_cardButtons[10].setBounds(205, 180, 75, 75);
        arr_cardButtons[11].setBounds(295, 180, 75, 75);
        
        // Sets the positions of the card JButtons in row 4
        arr_cardButtons[12].setBounds(25, 270, 75, 75);
        arr_cardButtons[13].setBounds(115, 270, 75, 75);
        arr_cardButtons[14].setBounds(205, 270, 75, 75);
        arr_cardButtons[15].setBounds(295, 270, 75, 75);
        
        // Sets the positions of the JButtons required for main program functionality
        playButton.setBounds(25, 400, 105, 25);
        exitButton.setBounds(270, 400, 105, 25);
        
        // Sets the text of the JLabels
        instructions1Label.setText("Click the Play button to start. Make sure to click Guess Again after");
        instructions2Label.setText("each guess.");
        
        // Sets the positions of the JLables
        instructions1Label.setBounds(5, 390, 400, 100);
        instructions2Label.setBounds(5, 410, 400, 100);
        
        // Sets the text of the buttons
        playButton.setText("Play Again");
        exitButton.setText("Exit");
        
        // Adds the JComponents to container
        container.add(playButton);
        container.add(exitButton);
        container.add(instructions1Label);
        container.add(instructions2Label);
        
        pack();
        
        setVisible(true);    // Shows the JFrame
        setResizable(false); // Prevents the user from resizing the JFrame
        requestFocus();      // Sets the focus to the JFrame
        
        newGame();
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent evt) {
                System.exit(0); // Closes MatchingGameJFrame
            }
        });
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent evt)  {
               newGame(); // Sets up a new game
           }
        });
        
        // arr_cardButtons methods ---------------------------------------------
        // All of these methods call the method cardClicked and pass an int 
        // parameter to this method
        arr_cardButtons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(0);
            }
        });
        
        arr_cardButtons[1].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(1);
            } 
        });
        
        arr_cardButtons[2].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(2);
            } 
        });
        
        arr_cardButtons[3].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(3);
            } 
        });
        
        arr_cardButtons[4].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(4);
            } 
        });
        
        arr_cardButtons[5].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(5);
            } 
        });
        
        arr_cardButtons[6].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(6);
            } 
        });
        
        arr_cardButtons[7].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(7);
            } 
        });
        
        arr_cardButtons[8].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(8);
            } 
        });
        
        arr_cardButtons[9].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(9);
            } 
        });
        
        arr_cardButtons[10].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(10);
            } 
        });
        
        arr_cardButtons[11].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(11);
            } 
        });
        
        arr_cardButtons[12].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(12);
            } 
        });
        
        arr_cardButtons[13].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(13);
            } 
        });
        
        arr_cardButtons[14].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(14);
            } 
        });
        
        arr_cardButtons[15].addMouseListener(new MouseAdapter() {
           @Override
            public void mouseClicked(MouseEvent evt) {
                cardClicked(15);
            } 
        });
        // End of arr_cardButtons methods --------------------------------------
    }
    
    /**
     * Sets up a new game.
     * 
     * Precondition:  Called from playButton or constructor
     * Postcondition: All field variables and arrays initialized properly
     */
    private void newGame() {
       // Initialies each element of arr_change to the value 1 to later be 
       // used to check if a match has been made
       for (int int_i = 0; int_i < 16; int_i++) {
           arr_change[int_i] = 1; // Sets arr_change's elements to 1
       }

       // Temperarily holds an index as a String to add the number to arl_set
       String str_index;

       // Puts the numbers 0 to 7 in arl_set
       for (int int_i = 0; int_i < 8; int_i++) {
           // Puts the numbers in two consecutive array entries
           for (int int_j = 1; int_j <= 2; int_j++) {
               str_index = Integer.toString(int_i); // Coverts int_i to a String
               arl_set.add(str_index); // Stores int_i in the next available position
           }
       }

       // Places the cards into a randomized array
       for (int int_i = 0; int_i < 16; int_i++) {
           // Creates a random number and converts it to an integer
           double doub_index = Math.floor(Math.random() * (16 - int_i));
           int int_index = (int) doub_index;

           // Adds int_index1 to arl_cards and removes it from arl_set
           arl_cards.add(arl_set.get(int_index));  // Adds int_index1 to arl_cards
           arl_set.remove(arl_set.get(int_index)); // Removes int_index from arl_set
       }

       // Resets the text of instructions1Label and instructions2Label
       instructions1Label.setText("Click the Play button to start. Make sure to click Guess Again after");
       instructions2Label.setText("each guess.");
    }
    
    /**
     * Checks to see what image is associated with the card at index int_index, 
     * assigns this image to the card, and processes the clicked card.
     * 
     * Precondition:  Called from a mouse clicked event
     * Postcondition: processClickedCard called and lastButtonClicked updated
     * 
     * @param int_index The index of the clicked card
     */
    public void cardClicked(int int_index) {
        // Prevents the user from doing anything until they press Play Again
        if (int_cardsleft == 0) return;
        
        // Tells the user they clicked the same card button twice
        if (lastButtonClicked == arr_cardButtons[int_index]) {
            instructions1Label.setText("You already clicked this card.");
            instructions2Label.setText("Please try another card.");
            return;
        }
        
        // Checks to see what random picture is placed in the box
        String str_temp = arl_cards.get(int_index);

        if (int_cardsClicked == 1) { // Display clicked icon for a while
            // Assigns the proper ImageIcon to the card
            switch (str_temp) {
                case "0":
                    arr_cardButtons[int_index].setIcon(ii_a); // If str_temp.equals("0"), sets icon to ii_a
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "1":
                    arr_cardButtons[int_index].setIcon(ii_b); // If str_temp.equals("1"), sets icon to ii_b
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "2":
                    arr_cardButtons[int_index].setIcon(ii_c); // If str_temp.equals("2"), sets icon to ii_c
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "3":
                    arr_cardButtons[int_index].setIcon(ii_d); // And so on ...
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "4":
                    arr_cardButtons[int_index].setIcon(ii_e);
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "5":
                    arr_cardButtons[int_index].setIcon(ii_f);
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "6":
                    arr_cardButtons[int_index].setIcon(ii_g);
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                case "7":
                    arr_cardButtons[int_index].setIcon(ii_h);
                    
                    // Delay
                    try {
                        Thread.sleep(int_timeToWait);
                    }
                    catch (InterruptedException ie) {
                        // Don't need to do anything
                    }
                    
                    break;
                default:
                    arr_cardButtons[int_index].setIcon(ii_back); // Icon is set to ii_back by default
                    break;
            }
        }
        else if (int_cardsClicked == 0) { // Display icon until told not to
            // Assigns the proper ImageIcon to the card
            switch (str_temp) {
                case "0":
                    arr_cardButtons[int_index].setIcon(ii_a); // If str_temp.equals("0"), sets icon to ii_a
                    break;
                case "1":
                    arr_cardButtons[int_index].setIcon(ii_b); // If str_temp.equals("1"), sets icon to ii_b
                    break;
                case "2":
                    arr_cardButtons[int_index].setIcon(ii_c); // If str_temp.equals("2"), sets icon to ii_c
                    break;
                case "3":
                    arr_cardButtons[int_index].setIcon(ii_d); // And so on ...
                    break;
                case "4":
                    arr_cardButtons[int_index].setIcon(ii_e);
                    break;
                case "5":
                    arr_cardButtons[int_index].setIcon(ii_f);
                    break;
                case "6":
                    arr_cardButtons[int_index].setIcon(ii_g);
                    break;
                case "7":
                    arr_cardButtons[int_index].setIcon(ii_h);
                    break;
                default:
                    arr_cardButtons[int_index].setIcon(ii_back); // Icon is set to ii_back by default
                    break;
            }
        }

        // Delay
        try {
            Thread.sleep(int_timeToWait);
        }
        catch (InterruptedException ie) {
            // Don't need to do anything
        }
        
        // Sets the card chosen (first or second card chosen) to one or two so 
        // it can be checked for a match
        int_cardsClicked++;
        
        processClickedCard(int_index);                  // Processes the clicked card
        lastButtonClicked = arr_cardButtons[int_index]; // Updates lastButtonClicked

        // Lets the user know they have successfully completed the game
        if (int_cardsleft == 0) {
            instructions1Label.setText("Congratulations, you may play again.");
            instructions2Label.setText("");
        }
    }
    
    /**
     * Processes the clicked card. If the cards match, the cards are set to 
     * ii_done. If the cards do not match, the cards are set to ii_back.
     * 
     * Precondition:  Called from cardClicked method
     * Postcondition: Cards display the appropriate icon
     * 
     * @param int_index The index of the clicked card
     */
    public void processClickedCard(int int_index) {
        // Assigns clicked card to the user's first choice of cards
        if (int_cardsClicked == 1) {
            int_c1 = Integer.parseInt(arl_cards.get(int_index)); // Sets int_c1 to arl_card's first element
            arr_change[int_index] = 0; // Inidicates the card icon has been used
        }
        // Assigns clicked card to the user's second choice of cards
        else if (int_cardsClicked == 2) {
            if (lastButtonClicked.getIcon() == arr_cardButtons[int_index]) {
                // Sets the two buttons' icons to ii_done
                arr_cardButtons[getIndexOfLastButtonClicked(int_index)].setIcon(ii_done);
                arr_cardButtons[int_index].setIcon(ii_done);
            }
            else { // The two cards clicked do not have the same image
                // Sets the two buttons' icons to ii_back
                arr_cardButtons[getIndexOfLastButtonClicked(int_index)].setIcon(ii_back);
                arr_cardButtons[int_index].setIcon(ii_back);
            }
            
            int_cardsClicked = 0;      // Resets int_cardsClicked
            int_c2 = Integer.parseInt(arl_cards.get(int_index)); // Sets int_c2 to arl_card's first element
            arr_change[int_index] = 0; // Indicates the card icon has been used
            lastButtonClicked = null;  // Sets lastButtonClicked to null
        }
    }
    
    /**
     * Returns the index of the last button clicked as an int.
     * 
     * Precondition:  Called from processCardClicked method
     * Postcondition: Index of button with same icon as lastButtonClicked returned
     * 
     * @param int_index The index of the card that was clicked
     * @return          The index of the last button clicked
     */
    public int getIndexOfLastButtonClicked(int int_index) {
        int int_temp = 0; // Stores int_i from the for loop below
        
        for (int int_i = 0; int_i < arr_cardButtons.length; int_i++) {
            if (int_i != int_index 
                    && arr_cardButtons[int_i].getIcon() 
                    == lastButtonClicked.getIcon()) {
                int_temp = int_i; // Sets int_temp to int_i
                break;            // Quits for loop
            }
        }
        
        return int_temp; // Returns int_temp
    }
}
