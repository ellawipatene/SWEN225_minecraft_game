package main;

import java.awt.Dimension;
import java.awt.Graphics;

import imgs.Img;

class Monster implements Entity{
  Point location;
  State monsterState = new AwakeState();
  public Img face = Img.AwakeMonster;
  public final double speed = 0.05d;
  
  public double speed(){ return speed; }
  public Point location(){ return location; }
  public void location(Point p){ location=p; }
  Monster(Point location){ this.location=location; }
  Monster(Point location, State s){ 
	  this.location=location; 
	  this.monsterState = s;
  }

  public void ping(Model m){
    monsterState.ping(this, m);
  }
 
  public void draw(Graphics g, Point center, Dimension size){ 
	  drawImg(face.image, g, center, size); 
  }
}

interface State {
    void ping(Monster m, Model n);
}

record AwakeState() implements State {
    public void ping(Monster m, Model n) {
        m.face = Img.AwakeMonster;
        var arrow = n.camera().location().distance(m.location);
        double size = ((Point) arrow).size();
        arrow = ((Point) arrow).times(m.speed() / size);
        m.location = m.location.add(arrow);
        if(size > 6) m.monsterState = new SleepState(); // Go back to sleep if camera if far
        if(size < 0.6) n.onGameOver(); // Kill the player
    }
}

record SleepState() implements State {
    public void ping(Monster m, Model n) {
        m.face = Img.SleepMonster;
        var arrow = n.camera().location().distance(m.location);
        double size = ((Point) arrow).size();
        if(size < 6) m.monsterState = new AwakeState(); // Wake monster if camera is close
    }
}

record DeadState() implements State {
    static int count;
    DeadState(){ count = 0; }
    public void ping(Monster m, Model n) {
    	m.face = Img.DeadMonster;
    	if(count++ == 100) n.remove(m); // after 100 pings, monster disapears
    }
}

record RoamingState() implements State {
    static int count;
    static Point randPoint = new Point(Math.random() * 16, Math.random() * 16);

    public void ping(Monster m, Model n) {
        count++;
        m.face = Img.AwakeMonster;
        if (count == 50) { // Move to a random point every 50 counts
            randPoint = new Point(Math.random() * 16, Math.random() * 16); 
            count = 0;
        }
        var arrow = randPoint.distance(m.location());
        arrow = arrow.times(m.speed() / arrow.size());
        double size = ((Point) arrow).size();
        arrow = ((Point) arrow).times(m.speed() / size);
        m.location = m.location().add(arrow);
        if(n.camera().location().distance(m.location).size() < 0.6) n.onGameOver(); // Kill the player
    }
}