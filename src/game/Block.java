
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Block extends GameObject {

    private BufferedImage block_image;
    
    public Block(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        block_image= ss.grabImage(4, 0, 64, 64);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
    
}
