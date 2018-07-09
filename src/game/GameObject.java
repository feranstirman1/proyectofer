/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author LVS
 */
public abstract class GameObject {
    
    protected int x,y;
    protected int velX=0,velY=0;
    protected ID id;
    protected SpriteSheet ss;
    
    public GameObject(int x,int y,ID id,SpriteSheet ss){
        this.x=x;
        this.y=y;
        this.id=id;
        this.ss=ss;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    ////////////////////////GETTERS Y SETTERS /////////////////////////////////////////////

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    
}
