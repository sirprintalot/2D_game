package main;


import javax.swing.*;
import java.util.*;

public class Main {
        
    public static JFrame window;
    
    public static void main(String[] args) {
                                                           
        //window                     
        window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");

        new Main().setIcon();

        //game panel
        GamePanel gamePanel = new GamePanel();  

        //add game panel to window
        window.add(gamePanel);              

        // Load configurations                        
        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn){
            window.setUndecorated(true);
        }

        window.pack();

        //window position and visibility
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //set the objects and npc's and the game state
        gamePanel.setUpGame();  //must be before the game thread

        //run the game thread
        gamePanel.startGameThread();
    }

    public void setIcon(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("player/boy_down_1.png")));

        window.setIconImage(imageIcon.getImage());
    }
}
 