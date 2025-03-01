package environment;

import main.*;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public void setup(){
        
        //TODO
        // the circle size must be dynamic depending on the light source
        lighting = new Lighting(gp, 320);
    }

    public void draw(Graphics2D g2){

        lighting.draw(g2);
    }



}
