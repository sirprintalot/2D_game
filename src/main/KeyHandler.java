package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    
    //DEBUG
    public boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //title state
        if(gp.gameState == gp.tittleState){
            
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum --;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum ++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        }

        if(code == KeyEvent.VK_ENTER){

            if(gp.ui.commandNum == 0){

                gp.gameState = gp.playState;
                //BUG music keep reproducing while pressing enter
//                gp.playMusic(0);

            }
            if(gp.ui.commandNum == 1){

                //TODO
            }
            
            if(gp.ui.commandNum == 2){

                 System.exit(0);

            }
        }

        //play state
        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {

                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER){
 
                enterPressed = true;
            }

            //DEBUG
            if (code == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;
            }
        }

        //pause state

        else if(gp.gameState == gp.pauseState){

            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }

        }

        //exit dialogue state
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
                enterPressed = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

    }
}
