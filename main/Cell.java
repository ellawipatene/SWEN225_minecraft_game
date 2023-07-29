package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import imgs.Img;

record Cell(List<Img> imgs, int x, int y){
  static final public int renderX=100;
  static final public int renderY=60;

  void draw(Graphics g, Point center, Dimension size){
    int w1=x*Cell.renderX-(int)(center.x()*Cell.renderX);
    int h1=y*Cell.renderY-(int)(center.y()*Cell.renderY);
    int w2=w1+Cell.renderX;
    int h2=h1+Cell.renderY;
    var isOut=h2<=0 || w2<=0 || h1>=size.height || w1>=size.width;
    if(isOut){ return; }
    imgs.forEach(i->g.drawImage(
      i.image,w1,h1,w2,h2,0,0,Cell.renderX,Cell.renderY,null));
  }
}