
package game;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author feranstirman
 */
public class Window {
    
    public Window(int width,int height,String title,Game game){
        
        width=900;
        height=700;
        int scale=1;
        
        JFrame frame= new JFrame(title);
        
        frame.setPreferredSize(new Dimension(width*scale,height*scale));
        frame.setMaximumSize(new Dimension(width*scale,height*scale));
        frame.setMinimumSize(new Dimension(width*scale,height*scale));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        
    }
    
    
}
