package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");
 
        GamePanel gamePanel = new GamePanel();
         window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);    
                                                                                
        gamePanel.setUpGame(); //tiene que estar antes de que comience el juego
        gamePanel.startGameThread();                                                           
        
    }
}

