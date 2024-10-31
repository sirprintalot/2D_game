package main;

import entity.*;
import tile.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

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

    //Event Handler
    public EventHandler eventHandler = new EventHandler(this);

    //Game thread
    Thread gameThread;

    //player
    public Player player = new Player(this, keyH);  // tiene que ser public

    //object
    public Entity[] obj = new Entity[10];               //[]cantidad de objetos

    //NPC's
    public Entity[] npc = new Entity[10];

    //Monsters
    public Entity[] monster = new Entity[20];

    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int tittleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int interactState = 5;


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
        //play the game theme on loop
//        playMusic(0);
//        stopMusic();
        gameState = tittleState;

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
                repaint();
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
                        monster[i] = null;
                    }
                }

            }
        }

        if (gameState == pauseState) {
            //TO DO

        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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
            //enhanced method
            entityList.sort(Comparator.comparingInt(entity -> entity.worldY));

            //Sort the order of entities based on the y position old method
//            Collections.sort(entityList, new Comparator<Entity>() {
//                @Override
//                public int compare(Entity entity1, Entity entity2) {
//
//                    return Integer.compare(entity1.worldY, entity2.worldY);
//                }
//            });

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

            g2.setFont(new Font("Arial",Font.PLAIN, 25));

            int textX = 20;
            int textY = 480;
            int lineHeight = 20;

            g2.drawString("Player's world X: " + player.worldX, textX, textY);
            textY += lineHeight;

            g2.drawString("Player's world Y: " + player.worldY, textX, textY);
            textY += lineHeight;

            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, textX, textY);
            textY += lineHeight;

            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, textX, textY);
            textY += lineHeight;

            g2.setColor(Color.white);
            g2.drawString("Time passed: " + passedTime, textX, textY);
        }

        g2.dispose();
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
