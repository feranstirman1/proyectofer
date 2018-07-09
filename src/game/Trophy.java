package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Trophy extends GameObject {

    private BufferedImage water_image;
    
    public Trophy(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        water_image= ss.grabImage(3, 0, 64, 64);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 64, 64);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
    
}

