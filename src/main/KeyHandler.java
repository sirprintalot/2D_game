package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shootPressed;

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

        // Option state
        else if (gp.gameState == gp.optionState) {
            optionState(code);
        }

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //TRADE STATE
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        //Map STATE
        else if (gp.gameState == gp.mapState) {
            mapState(code);
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
//                    gp.ui.titleScreenState = 1;
                    gp.gameState = gp.playState; //without title screen substate
                    gp.stopMusic();
                    //play the game theme on loop
                    gp.playMusic(0);
                    System.out.println("play music");
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
    }

    //character selection screen

//        else if (gp.ui.titleScreenState == 1) {
//
//            if (code == KeyEvent.VK_W) {
//                gp.ui.commandNum--;
//                if (gp.ui.commandNum < 0) {
//                    gp.ui.commandNum = 3;
//                }
//            }
//            if (code == KeyEvent.VK_S) {
//                gp.ui.commandNum++;
//                if (gp.ui.commandNum > 3) {
//                    gp.ui.commandNum = 0;
//                }
//            }
//            if (code == KeyEvent.VK_ENTER) {
//                if (gp.ui.commandNum == 0) {
//
//                    System.out.println("select fighter character with specific stats");
//                    gp.gameState = gp.playState;
//                }
//                if (gp.ui.commandNum == 1) {
//
//                    System.out.println("select thief character with specific stats");
//                    gp.gameState = gp.playState;
//                }
//
//                if (gp.ui.commandNum == 2) {
//
//                    System.out.println("select sorcerer character with specific stats");
//                    gp.gameState = gp.playState;
//
//                }
//
//                if (gp.ui.commandNum == 3) {
//
//                    //back option
//                    gp.ui.titleScreenState = 0;
//
//                }
//            }
//        }
//    }


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
        // shoot projectile
        if (code == KeyEvent.VK_F) {
            shootPressed = true;
        }

        // open character window
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
            System.out.println("char window");
        }

        // Pause game
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.stopMusic();

        }

        // option menu
        if (code == KeyEvent.VK_ESCAPE) {

            gp.gameState = gp.optionState;

        }
        // map
        if (code == KeyEvent.VK_M) {

            gp.gameState = gp.mapState;

        }
        if (code == KeyEvent.VK_X) {

            if(!gp.map.miniMapOn){
               gp.map.miniMapOn = true;
            }
            else{
                gp.map.miniMapOn = false;
            }

        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            debugFunc = !debugFunc;
        }

        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0 -> gp.tileM.loadMap("/maps/worldV3.txt", 0);
                case 1 -> gp.tileM.loadMap("/maps/interior01.txt", 1);
            }

        }


    }

    public void mapState(int code){
         if(code == KeyEvent.VK_M){
             gp.gameState = gp.playState;
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
            gp.playMusic(0);
        }
    }

    public void optionState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }


        int maxCommandNum = 0;

        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;

            case 3:
                maxCommandNum = 1;
                break;
        }


        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSoundEffect(14);

            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSoundEffect(14);

            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }

        //sound volume control
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {

                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSoundEffect(14);
                    System.out.println("bajar volumen musica");
                    System.out.println(gp.music.volume);

                }
//                //sounf fx
                if (gp.ui.commandNum == 2 && gp.soundFX.volumeScale > 0) {
                    gp.soundFX.volumeScale--;
                    gp.playSoundEffect(14);
                    System.out.println("bajar volumen fx");
                    System.out.println(gp.soundFX.volume);
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {

                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSoundEffect(14);
                    System.out.println("subir musica");
                    System.out.println(gp.music.volume);
                }
                //sound fx
                if (gp.ui.commandNum == 2 && gp.soundFX.volumeScale < 5) {
                    gp.soundFX.volumeScale++;
                    gp.playSoundEffect(14);
                    System.out.println("subir fx");
                    System.out.println(gp.soundFX.volume);
                }
            }
        }


    }

    public void characterState(int code) {

        //exit character window
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }

        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }

        playerInventory(code);
    }

    public void playerInventory(int code) {

        //cursor movement
        if (code == KeyEvent.VK_W) {

            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_A) {

            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_D) {

            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSoundEffect(14);
            }
        }
    }

    public void npcInventory(int code){

        //cursor movement
        if (code == KeyEvent.VK_W) {

            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_A) {

            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSoundEffect(14);
            }
        }

        if (code == KeyEvent.VK_D) {

            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSoundEffect(14);
            }
        }
        
    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {

            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSoundEffect(14);
        }

        if (code == KeyEvent.VK_S) {

            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSoundEffect(14);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.tittleState;
                gp.playMusic(8);
                gp.restart();

            }

        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSoundEffect(14);
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSoundEffect(14);
            }
        }

        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }

        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
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
        if (code == KeyEvent.VK_F) {
            shootPressed = false;
        }

    }
}