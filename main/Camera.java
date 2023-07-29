package main;

import java.awt.Dimension;
import java.awt.Graphics;

import imgs.Img;

class Camera extends ControllableDirection implements Entity{
  private Point location;
  Camera(Point location){ this.location=location; }  
  public Point location(){ return location; }
  public void location(Point p){ location=p; }
  public Point arrow(){ return direction().arrow(0.1d); }
  public void ping(Model m) {
    location(location().add(arrow()));
  }
  public void draw(Graphics g,Point center,Dimension size){
    drawImg(Img.Hero.image, g, center, size);
    }
}