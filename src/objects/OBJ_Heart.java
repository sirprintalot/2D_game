package objects;

import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp){

        name = "heart";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
            image = utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2 = utilityTool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3 = utilityTool.scaledImage(image3, gp.tileSize, gp.tileSize);
        }   catch (IOException e){
            e.printStackTrace();
        }

    }







}
