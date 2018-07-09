package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Dragon extends GameObject {

    private BufferedImage portal_image;
    
    public Dragon(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        portal_image= ss.grabImage(2, 3, 128, 64);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(portal_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,128,64);
    }
    
}
