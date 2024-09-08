package objects;

import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Key extends SuperObject{

    GamePanel gp;

    public OBJ_Key(GamePanel gp){

        name = "key";

        try{
          image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
          utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }   catch (IOException e){
            e.printStackTrace();
        }

    }

    
}
