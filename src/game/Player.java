
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author feranstirman
 */
public class Player extends GameObject {

    
    Handler handler;
    Game game;
    
    //////////////////////////////// ANIMACIONES /////////////////////////////////////////////////////
    
    private BufferedImage[] player_down= new BufferedImage[9];
    private BufferedImage[] player_left= new BufferedImage[9];
    private BufferedImage[] player_right= new BufferedImage[9];
    private BufferedImage[] player_up= new BufferedImage[9];
    
    Animation animLeft;
    Animation animUp;
    Animation animRight;
    Animation animDown;
    Animation currenAnimation;
    
    
   /////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    
    public Player(int x, int y, ID id,Handler handler,SpriteSheet ss,Game game) {
        super(x, y, id,ss);
        this.handler=handler;
        this.game=game;
        
        for(int i=0;i<=8;i++){
            player_down[i]= ss.grabImage(i, 10, 64, 64);
            player_up[i]= ss.grabImage(i, 8, 64, 64);
            player_left[i]= ss.grabImage(i, 9, 64, 64);
            player_right[i]= ss.grabImage(i, 11, 64, 64);
        }
        
        animDown= new Animation(3,player_down);
        animLeft= new Animation(3,player_left);
        animRight= new Animation(3,player_right);
        animUp= new Animation(3,player_up);
        
        currenAnimation= animDown;
        
    }

    @Override
    public void tick() {
        
        
        x+=velX;
        y+=velY;
        
        collision();
        
        /////////////MOVIMIENTO////////////////////////////////////
        
        int vel=2;
        
        if(handler.isUp()){
            velY=-vel;
            currenAnimation= animUp;
        }
        else if(!handler.isDown())velY=0;
        
        if(handler.isDown()){ velY=vel;currenAnimation=animDown;}
        else if(!handler.isUp()) velY=0;
        
        if(handler.isRight()){ velX=vel; currenAnimation=animRight;}
        else if(!handler.isLeft()) velX=0;
        
        if(handler.isLeft()) {velX=-vel;currenAnimation=animLeft;}
        else if(!handler.isRight()) velX=0;
        /////////////////////////////////////////////////////////////////
        
        
        /////////////////////////// ANIMACION //////////////////////////////////////////////////////
        currenAnimation.runAnimation();
        
        ///////////////////////////////////////////////////////////////////////////////////////////
        
    }

    private void collision(){
        
        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId()== ID.Building){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    x+=velX*-1;
                    y+=velY*-1;
                }
                
            }
            
            if(tempObject.getId()== ID.Store){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    x+=velX*-1;
                    y+=velY*-1;
                    System.out.println("Se ha ingresado a la tienda!");
                }
                
            }
            
            if(tempObject.getId()== ID.Arena){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    
                    if(velX==0 && velY==0){
                        //NO PASA NADA
                    }else{
                    //FUNCION PARA ENTRAR EN BATALLA
                    System.out.println("buscando enemigos");}
                }
                
            }
            
            if(tempObject.getId()== ID.Portal){
                
                if(!game.isLevelPassed1() && !game.isLevelPassed2() && !game.isLevelPassed3() ){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    x+=velX*-1;
                    y+=velY*-1;
                    System.out.println("No ha pasado todos los niveles aun");
                }}
                
            }
            
            if(tempObject.getId()== ID.Dragon){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    if(game.isBossDefeated()){
                        //puede pasar
                    }else{
                        System.out.println("batalla final");
                        //meter funcion para batalla final
                        x+=velX*-1;
                        y+=velY*-1;
                    }
                }
                
            }
            
            if(tempObject.getId()== ID.Trophy){
                
                if(getBounds().intersects(tempObject.getBounds())){
                    if(game.isBossDefeated()){
                        //puede pasar
                        game.setIsRunning(false);
                        System.out.println("HA GANADO");
                    }
                }
                
            }
            
        }
        
    }
    
    @Override
    public void render(Graphics g) {
        if(velX==0 & velY==0){
            g.drawImage(player_down[0], x, y, null);
        }else{
            currenAnimation.drawAnimation(g, x, y, 0);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
    
}
