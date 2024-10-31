package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    //DEBUG
    public boolean debugFunc = false;

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
        if (gp.gameState == gp.tittleState) {
            titleState(code);
        }

        //play state
        else if (gp.gameState == gp.playState) {
            playState(code);
        }

        //pause state
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        //exit dialogue state
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        //Character state
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
    }

    // Title state method
    public void titleState(int code) {

        //check the sub-state
        if (gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                    //gp.gameState = gp.playState; without title screen substate
                    //BUG music keep reproducing while pressing enter
//                       gp.playMusic(0);

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        //character selection screen

        else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {

                    System.out.println("select fighter character with specific stats");
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {

                    System.out.println("select thief character with specific stats");
                    gp.gameState = gp.playState;
                }

                if (gp.ui.commandNum == 2) {

                    System.out.println("select sorcerer character with specific stats");
                    gp.gameState = gp.playState;

                }

                if (gp.ui.commandNum == 3) {

                    //back option
                    gp.ui.titleScreenState = 0;

                }
            }
        }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        //<=
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        // =>
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        //Attack interact
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        // open character window
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
            System.out.println("char window");
        }

        // Pause game
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;

        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            debugFunc = !debugFunc;
        }

        if (code == KeyEvent.VK_R) {
            gp.tileM.loadMap("/maps/worldV2.txt");
//            System.out.println("map loaded");
        }
        

    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
            enterPressed = false;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        //exit character window
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
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