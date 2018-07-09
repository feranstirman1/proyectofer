package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Lava extends GameObject {

    private BufferedImage lava_image;
    
    public Lava(int x, int y, ID id,SpriteSheet ss) {
        super(x, y, id,ss);
        lava_image= ss.grabImage(2, 0, 64, 64);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(lava_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
    
}

