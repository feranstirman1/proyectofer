
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Sand extends GameObject {

    private BufferedImage sand_image;
    
    public Sand(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        sand_image= ss.grabImage(1, 0, 64, 64);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sand_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
    
}