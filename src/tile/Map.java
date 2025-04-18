package tile;

import main.*;

import java.awt.*;
import java.awt.image.*;

public class Map extends TileManager {

    GamePanel gp;
    BufferedImage[] worldMap;
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;

        for (int i = 0; i < gp.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                int tileNum = mapTileNum[i][col][row];
                int x = gp.tileSize * col;
                int y = gp.tileSize * row;

                g2.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {

        //set the background color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw the map
        int width = 500;
        int height = 500;
        int x = gp.screenWidth / 2 - width / 2;
        int y = gp.screenHeight / 2 - height / 2;

        g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

        // Draw player
        double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
        int playerX = (int) (x + gp.player.worldX / scale);
        int playerY = (int) (y + gp.player.worldY / scale);
        int playerSize = (int) (gp.tileSize / scale + 8);

        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

        // Hint message
        g2.setFont(g2.getFont().deriveFont(23f));
        g2.setColor(Color.WHITE);
        g2.drawString("Press M to exit", 750, 550);

    }

    public void drawMiniMap(Graphics2D g2) {
        if (miniMapOn) {

            // Draw the map
            int width = 200;
            int height = 200;
            int x = gp.screenWidth - width - 3;
            int y = 3;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
            g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

            // Draw player
            double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
            int playerX = (int) (x + gp.player.worldX / scale);
            int playerY = (int) (y + gp.player.worldY / scale);
            int playerSize = (int) (gp.tileSize / 3.8);

            // draw the player with the offset
            g2.drawImage(gp.player.down1, playerX - 4, playerY - 4, playerSize, playerSize, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }


}
