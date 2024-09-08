package objects;

import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Door extends SuperObject{

    GamePanel gp;

    public OBJ_Door(GamePanel gp){

        name = "door";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }   catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
    

}
