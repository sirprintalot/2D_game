package main;

import entity.*;
import objects.*;
import tile.*;

import javax.swing.*;
import java.awt.*;

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
//    public final int worldWidht = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    //instances
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundFX = new Sound();

    public UI ui = new UI(this);
    public CollisionChecker cCheck = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    Thread gameThread;

    //entity & object
    public Player player = new Player(this, keyH);  // tiene que ser public
    public SuperObject[] obj = new SuperObject[10]; //[]cantidad de objetos

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        
        assetSetter.setObject();
        //play the game theme on loop
//        playMusic(0);
//        stopMusic();
        gameState = playState;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
//     METHOD 1
//    @Override
//    public void run() {
//
//        double drawInterval = (double) 1_000_000_000 /FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null){
//            //long currentTime = System.nanoTime();
//            update();
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

    //method 2 DELTA/ACUMULATOR
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

        if(gameState == playState){
            player.update(); 
        }

        if(gameState == pauseState){
               //TO DO
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime){
            
            drawStart = System.nanoTime();
        }


        //tiles
        tileM.draw(g2);
        //objects
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //player
        player.draw(g2);
        //UI
        ui.draw(g2);

        //debug
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passedTime = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Time elapsed: " + passedTime, 50, 550);
            System.out.println(passedTime);
        }



        g2.dispose();
    }

    //SOUND 
    public void playMusic(int songIndex){
        music.setFile(songIndex);
        music.play();
        music.loop();
    }

    public void stopMusic(){

        music.stop();
    }

    public void playSoundEffect(int i){

        soundFX.setFile(i);
        soundFX.play();
    }
}
