
package game;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author feranstirman
 */
public class Handler {
    
    LinkedList<GameObject> object= new LinkedList<GameObject>();
    
    private boolean up=false,right=false,down=false,left=false;
    
    /////////////////////////////////////GETTERS Y SETTERS/////////////////////////////////////////////////////////////

    public LinkedList<GameObject> getObject() {
        return object;
    }

    public void setObject(LinkedList<GameObject> object) {
        this.object = object;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void tick(){
        
        for(int i=0;i<object.size();i++){
            object.get(i).tick();
        }
        
    }
    
    public void render(Graphics g){
        for(int i=0;i<object.size();i++){
            object.get(i).render(g);
        }
    }
    
    public void addObject(GameObject newobject){
        object.add(newobject);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
