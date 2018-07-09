package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Casa extends GameObject {

    private BufferedImage store_image;
    
    public Casa(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        store_image= ss.grabImage(2, 1, 128, 128);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(store_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,128,128);
    }
    
}
