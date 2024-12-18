package entity;

import main.*;

import java.awt.*;

public class Particle extends Entity {

    Entity generator;
    Color color;
    int size;
    int xDirection;
    int yDirection;

    
    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;

        // The direction where the particle will fly to
        this.xDirection = xd;
        this.yDirection = yd;

        int offset = (gp.tileSize/2) - (size/2);
        life = maxLife;
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;


    }

    public void update(){

        life--;

        if(life < maxLife/3){
            yDirection++;
            size--;
        }

        worldX += xDirection * speed;
        worldY += yDirection * speed;

        if(life == 0){
            isAlive = false;
        }

    }

    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);

        g2.fillRect(screenX, screenY, size, size);



    }



}
