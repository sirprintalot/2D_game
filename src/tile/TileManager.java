package tile;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldV2.txt");

    }

    public void getTileImage() {

        //enhanced method
        //placeholders for 0 to 9
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);

        //real tiles

        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);


        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);


        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);


        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "teleport", false);

        //original method
//        try {
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tile[1].collision = true;
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
//            tile[2].collision = true;
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
//            tile[4].collision = true;
//            tile[5] = new Tile();
//            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public void setup(int index, String imageName, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image =
                    ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilityTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    public void loadMap() {
//        try {
//            InputStream is = getClass().getResourceAsStream("/maps/test_map_2.txt");
//            assert is != null;
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            int col = 0;
//            int row = 0;
//
//            while (col < gp.maxScreenCol && row < gp.maxScreenCol) {
//                String line = br.readLine();
//                while (col < gp.maxScreenCol) {
//                    String[] numbers = line.split("/");
//                    int num = Integer.parseInt(numbers[col]);
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if (col == gp.maxScreenCol) {
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            String line;
            while (row < gp.maxWorldCol) {  // Correct loop condition
                line = br.readLine();

                if (line == null) {  // Handle end of file
                    break;
                }

                String[] numbers = line.split(" ");  // Correct delimiter
                while (col < gp.maxWorldRow) {
                    if (col >= numbers.length) {
                        break;  // Prevent ArrayIndexOutOfBoundsException if there are fewer columns in the line
                    }
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                col = 0;  // Reset column counter for next row
                row++;    // Move to the next row
            }
            System.out.println("map loaded");
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);     test
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                //not necesary to add width and height the images are already scaled
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
