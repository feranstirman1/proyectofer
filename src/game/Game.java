
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
                
                if(red==255){
                    handler.addObject(new Block(xx*64,yy*64,ID.Building,ssTextures));
                }
                
                if(blue==255){
                    handler.addObject(new Player(xx*64,yy*64,ID.Player,handler,ssPlayer));
                }
                
            }
        }
    }
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    public static void main(String[] args){
        new Game();
    }

    

    
	

}