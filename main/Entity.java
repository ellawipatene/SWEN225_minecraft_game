package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

interface Entity{
  void ping(Model m);
  void draw(Graphics g, Point center, Dimension size);
  default void drawImg(BufferedImage img, Graphics g, Point center, Dimension size){
    var l = location();
    var lx = (l.x()-center.x())*Cell.renderX;
    var ly = (l.y()-center.y())*Cell.renderY;
    double iw = img.getWidth()/2d;
    double ih = img.getHeight()/2d;
    int w1 = (int)(lx-iw);
    int w2 = (int)(lx+iw);
    int h1 = (int)(ly-ih);
    int h2 = (int)(ly+ih);
    var isOut = h2<=0 || w2<=0 || h1>=size.height || w1>=size.width;
    if(isOut){ return; }
    g.drawImage(img,w1,h1,w2,h2,0,0,img.getWidth(),img.getHeight(),null);
    }

  Point location();
  default void location(Point p){
    throw new Error("This entity can not move");
  }
}