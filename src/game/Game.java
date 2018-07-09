
package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{
    
    private boolean isRunning=false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ssPlayer;
    private SpriteSheet ssTextures;
    
    private BufferedImage level=null;
    
    private BufferedImage ss_player=null;
    private BufferedImage ss_textures=null;
    private BufferedImage floor=null;
    
    private boolean levelPassed1=false;
    private boolean levelPassed2=false;
    private boolean levelPassed3=false;
    private boolean levelPassed4=false;
    private boolean bossDefeated=false;
    
    public Game(){
        
        handler= new Handler();
        camera= new Camera(0,0);
        
        this.addKeyListener(new KeyInput(this.handler));
        
        BufferedImageLoader loader= new BufferedImageLoader();
        
        level= loader.loadImage("/res/MUNDO1.png");
        
        
        ss_player= loader.loadImage("/res/player.png");
        ss_textures= loader.loadImage("/res/textures.png");
        
        ssPlayer= new SpriteSheet(ss_player);
        ssTextures= new SpriteSheet(ss_textures);
        
        floor= ssTextures.grabImage(0, 0, 64, 64);
        
        loadLevel(level);
        
        new Window(160,200,"UNTITLED",this);
        start();
    }
    
    private void start(){
        isRunning=true;
        thread=new Thread(this);
        thread.start();
    }
    
    private void stop() {
        isRunning=false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void run() {
        this.requestFocus();
        long lastTime= System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        long timer= System.currentTimeMillis();
        int frames=0;
        while(isRunning){
            long now= System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            while(delta>=1){
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis()-timer>=1000){
                timer+=1000;
                frames=0;
                //updates=0;
            }
        }
        stop();
    }
    
    public void tick(){
        
        for(int i=0;i<handler.object.size();i++){
            if(handler.object.get(i).getId()== ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
        
        handler.tick();
        
        
        
    }
    
    public void render(){
        BufferStrategy bs= this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g= bs.getDrawGraphics();
        Graphics2D g2d= (Graphics2D) g;
        ///////////////////////////////////////////////////////////////////////
        
        
        
        g2d.translate(-camera.getX(), -camera.getY());
        
        
        for(int xx=0;xx<2048*2;xx+=64){
            for(int yy=0;yy<2048*2;yy+=64){
                g.drawImage(floor, xx, yy, null);
            }
        }
        
        handler.render(g);
        
        g2d.translate(camera.getX(), camera.getY());
        
        ///////////////////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    /////////////////////////////////LEVEL LOADER ////////////////////////////////////////////////
    
    private void loadLevel(BufferedImage image){
        int w= image.getWidth();
        int h=image.getHeight();
        
        for(int xx=0;xx<w;xx++){
            for(int yy=0;yy<h;yy++){
                int pixel= image.getRGB(xx, yy);
                int red= (pixel >>16)&0xff;
                int green= (pixel >>8)&0xff;
                int blue= (pixel)&0xff;
                
                if(red==255 && green==0 && blue==0){
                    handler.addObject(new Block(xx*64,yy*64,ID.Building,ssTextures));
                }
                
                if(blue==255 && red==0 && green==0){
                    //handler.addObject(new Player(xx*64,yy*64,ID.Player,handler,ssPlayer,this));
                }
                
                if(blue==0 && red==0 && green==255){
                    handler.addObject(new Water(xx*64,yy*64,ID.Building,ssTextures));
                }
                
                if(blue==255 && red==255 && green==0){
                    handler.addObject(new Sand(xx*64,yy*64,ID.Arena,ssTextures));
                }
                
                if(blue==255 && red==255 && green==255){
                    handler.addObject(new Store(xx*64,yy*64,ID.Store,ssTextures));
                }
                
                if(blue==0 && red==255 && green==255){
                    handler.addObject(new Portal(xx*64,yy*64,ID.Portal,ssTextures));
                }
                
                if(blue==0 && red==0 && green==0){
                    handler.addObject(new Lava(xx*64,yy*64,ID.Building,ssTextures));
                }
                
                if(blue==255 && red==0 && green==255){
                    handler.addObject(new Casa(xx*64,yy*64,ID.Building,ssTextures));
                }
                
                if(blue==124 && red==186 && green==0){
                    handler.addObject(new Dragon(xx*64, yy*64, ID.Dragon, ssTextures));
                }
                
                if(blue==0 && red==219 && green==150){
                    handler.addObject(new Trophy(xx*64,yy*64,ID.Trophy,ssTextures));
                }
                
            }
        }
        
        //RENDERIZA AL JUGADOR DE ULTIMO PARA QUE QUEDE ENCIMA DE TODO
        for(int xx=0;xx<w;xx++){
            for(int yy=0;yy<h;yy++){
                int pixel= image.getRGB(xx, yy);
                int red= (pixel >>16)&0xff;
                int green= (pixel >>8)&0xff;
                int blue= (pixel)&0xff;
                
                if(blue==255 && red==0 && green==0){
                    handler.addObject(new Player(xx*64,yy*64,ID.Player,handler,ssPlayer,this));
                }
                
            }
        }
        
        
    }
    
    /////////////////////////////GETTERS Y SETTERS///////////////////////////////////////////////////

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isBossDefeated() {
        return bossDefeated;
    }

    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }

    

    public boolean isLevelPassed1() {
        return levelPassed1;
    }

    public void setLevelPassed1(boolean levelPassed1) {
        this.levelPassed1 = levelPassed1;
    }

    public boolean isLevelPassed2() {
        return levelPassed2;
    }

    public void setLevelPassed2(boolean levelPassed2) {
        this.levelPassed2 = levelPassed2;
    }

    public boolean isLevelPassed3() {
        return levelPassed3;
    }

    public void setLevelPassed3(boolean levelPassed3) {
        this.levelPassed3 = levelPassed3;
    }

    public boolean isLevelPassed4() {
        return levelPassed4;
    }

    public void setLevelPassed4(boolean levelPassed4) {
        this.levelPassed4 = levelPassed4;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public static void main(String[] args){
        new Game();
    }

    

    
	

}