package main;

import entity.*;
import tile.*;
import tile_interactive.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    //public final int maxScreenCol = 16; only for small screen
    public final int maxScreenCol = 20; //full screen
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60;

    //instances
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundFX = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker cCheck = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    // Config
    Config config = new Config(this);

    //Event Handler
    public EventHandler eventHandler = new EventHandler(this);

    //Game thread
    Thread gameThread;

    //player
    public Player player = new Player(this, keyH);  // tiene que ser public

    //object
    public Entity[] obj = new Entity[50];               //[]cantidad de objetos

    //NPC's
    public Entity[] npc = new Entity[10];

    //Monsters/Entities/projectiles
    public Entity[] monster = new Entity[20];

    ArrayList<Entity> entityList = new ArrayList<>();

    public ArrayList<Entity> projectileList = new ArrayList<>(); //must be public 16:27

    //Interactive tiles
    public InteractiveTile[] inTile = new InteractiveTile[50];

    //Particles
    public ArrayList<Entity> particleList = new ArrayList<>();


    //GAME STATE
    public int gameState;
    public final int tittleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {

        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();
        assetSetter.setInteractiveTiles();

        gameState = tittleState;
        playMusic(8);

        //create a temporal screen with the width and height of the original screen
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        g2 = tempScreen.createGraphics();

        if (fullScreenOn) {
            
            setFullScreen();
        }
        
    }

    public void setFullScreen() {

        // Enable macOS full-screen behavior
        System.setProperty("apple.awt.fullscreenable", "true");

        // Apply settings before the frame becomes visible
        Main.window.dispose(); // Remove the window temporarily
        Main.window.setUndecorated(true); // Set undecorated
        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        Main.window.setVisible(true); // Make the frame visible again

        // Update screen dimensions for Retina displays
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenWidth2 = (int) (gd.getDefaultConfiguration().getBounds().getWidth());
        screenHeight2 = (int) (gd.getDefaultConfiguration().getBounds().getHeight());


//        // get local screen size
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//
//        gd.setFullScreenWindow(Main.window);
//
//        // GET FULLSCREEN WIDTH AND HEIGHT
//        screenWidth2 = Main.window.getWidth();
//        screenHeight2 = Main.window.getHeight();
//
//        System.out.println(screenWidth2);
//        System.out.println(screenHeight2);
    }

    public void retry(){

        player.setDefaultPosition();
        player.restorePlayerStats();
        assetSetter.setNpc();
        assetSetter.setMonster();
        playMusic(0);
    }

    public void restart(){

        player.setDefaultValues();
        player.setItems();
        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();
        assetSetter.setInteractiveTiles();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //method 2 DELTA/ACCUMULATOR
    @Override
    public void run() {

        double drawInterval = (double) 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
//                repaint();   work only with paintComponent method
                drawToTempScreen();   //draw everything to the buffered image
                drawToScreen();       // draw the buffered image onto the screen
                delta--;
            }
        }
    }

    public void update() {

        if (gameState == playState) {

            //player
            player.update();

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            // Monster
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {

                    if (monster[i].isAlive && !monster[i].dying) {
                        monster[i].update();
                    }
                    if (!monster[i].isAlive) {

                        monster[i].checkItemDrop();
                        monster[i] = null;
                    }
                }

            }
            // Projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {

                    if (projectileList.get(i).isAlive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).isAlive) {
                        projectileList.remove(i);
                    }
                }
            }
            // Interactive tiles
            for (int i = 0; i < inTile.length; i++) {
                if (inTile[i] != null) {
                    inTile[i].update();
                }
            }

            // Particles from interactive tiles
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {

                    if (particleList.get(i).isAlive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).isAlive) {
                        particleList.remove(i);
                    }
                }
            }

        }
        if (gameState == pauseState) {
            //TO DO

        }

    }

    // New method for drawing the game
    public void drawToTempScreen() {

        //DEBUG
        long drawStart = 0;
        if (keyH.debugFunc) {

            drawStart = System.nanoTime();
        }

        //Tittle State
        if (gameState == tittleState) {
            ui.draw(g2);
        }

        //play State
        else {
            //tiles
            tileM.draw(g2);

            // Interactive tiles
            for (int i = 0; i < inTile.length; i++) {
                if (inTile[i] != null) {
                    inTile[i].draw(g2);
                }
            }
            //entity List
            //add player
            entityList.add(player);

            //add npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            //add objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            // Add monsters
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            // Projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            //Particles
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }
            //enhanced method
            entityList.sort(Comparator.comparingInt(entity -> entity.worldY));

            // Draw entities
            for (int i = 0; i < entityList.size(); i++) {

                entityList.get(i).draw(g2);
            }

            entityList.clear();

            //UI
            ui.draw(g2);
        }

        //debug
        if (keyH.debugFunc) {
            long drawEnd = System.nanoTime();
            long passedTime = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 25));

            int textX = 20;
            int textY = 480;
            int lineHeight = 20;

            g2.drawString("Player's world X: " + player.worldX, textX, textY);
            textY += lineHeight;

            g2.drawString("Player's world Y: " + player.worldY, textX, textY);
            textY += lineHeight;

            g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, textX, textY);
            textY += lineHeight;

            g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, textX, textY);
            textY += lineHeight;

            g2.setColor(Color.white);
            g2.drawString("Time passed: " + passedTime, textX, textY);
        }

    }


    //This method is no longer necessary because we are drawing everything into the temp screen,
    //so if we change, the resolution only has to resize the temp screen instead of drawing
    // all the components to the JPanel
////    public void paintComponent(Graphics g) {
////
////        super.paintComponent(g);
////        Graphics2D g2 = (Graphics2D) g;
////
////        //DEBUG
////        long drawStart = 0;
////        if (keyH.debugFunc) {
////
////            drawStart = System.nanoTime();
////        }
////
////        //Tittle State
////        if (gameState == tittleState) {
////            ui.draw(g2);
////        }
////
////        //play State
////        else {
////            //tiles
////            tileM.draw(g2);
////
////            // Interactive tiles
////            for (int i = 0; i < inTile.length; i++) {
////                if (inTile[i] != null) {
////                    inTile[i].draw(g2);
////                }
////            }
////            //entity List
////            //add player
////            entityList.add(player);
////
////            //add npc
////            for (int i = 0; i < npc.length; i++) {
////                if (npc[i] != null) {
////                    entityList.add(npc[i]);
////                }
////            }
////
////            //add objects
////            for (int i = 0; i < obj.length; i++) {
////                if (obj[i] != null) {
////                    entityList.add(obj[i]);
////                }
////            }
////
////            // Add monsters
////            for (int i = 0; i < monster.length; i++) {
////                if (monster[i] != null) {
////                    entityList.add(monster[i]);
////                }
////            }
////            // Projectile
////            for (int i = 0; i < projectileList.size(); i++) {
////                if (projectileList.get(i) != null) {
////                    entityList.add(projectileList.get(i));
////                }
////            }
////
////            //Particles
////            for (int i = 0; i < particleList.size(); i++) {
////                if (particleList.get(i) != null) {
////                    entityList.add(particleList.get(i));
////                }
////            }
////            //enhanced method
////            entityList.sort(Comparator.comparingInt(entity -> entity.worldY));
////
////            //Sort the order of entities based on the y position old method
//////            Collections.sort(entityList, new Comparator<Entity>() {
//////                @Override
//////                public int compare (Entity entity1, Entity entity2) {
//////
//////                    return Integer.compare(entity1.worldY, entity2.worldY);
//////                }
//////            });
////
////            // Draw entities
////            for (int i = 0; i < entityList.size(); i++) {
////
////                entityList.get(i).draw(g2);
////            }
////
////            entityList.clear();
////
////            //UI
////            ui.draw(g2);
////
////
////        }
//
//        //debug
//        if (keyH.debugFunc) {
//            long drawEnd = System.nanoTime();
//            long passedTime = drawEnd - drawStart;
//
//            g2.setFont(new Font("Arial", Font.PLAIN, 25));
//
//            int textX = 20;
//            int textY = 480;
//            int lineHeight = 20;
//
//            g2.drawString("Player's world X: " + player.worldX, textX, textY);
//            textY += lineHeight;
//
//            g2.drawString("Player's world Y: " + player.worldY, textX, textY);
//            textY += lineHeight;
//
//            g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, textX, textY);
//            textY += lineHeight;
//
//            g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, textX, textY);
//            textY += lineHeight;
//
//            g2.setColor(Color.white);
//            g2.drawString("Time passed: " + passedTime, textX, textY);
//        }
//
//        g2.dispose();
//    }

    public void drawToScreen() {

        Graphics g = getGraphics();

        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();

    }


    //SOUND 
    public void playMusic(int songIndex) {
        music.setFile(songIndex);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSoundEffect(int i) {

        soundFX.setFile(i);
        soundFX.play();
    }
}
