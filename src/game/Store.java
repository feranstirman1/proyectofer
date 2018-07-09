
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Store extends GameObject {

    private BufferedImage store_image;
    
    public Store(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        store_image= ss.grabImage(0, 1, 128, 128);
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
