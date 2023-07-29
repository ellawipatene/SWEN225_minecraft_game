package main;

record Point(double x, double y){
  //Note: x==with, y==height
  public Point add(double x,double y){
    return new Point(x()+x, y()+y);
  }
  public Point add(Point p){
    return add(p.x, p.y);
  }
  public Point times(double x, double y) {
    return new Point(x()*x, y()*y);
  }
  public Point times(double v) {
    return new Point(x()*v, y()*v);
  }
  
  public Point distance(Point other){
    return this.add(other.times(-1));
  }
  
  public double size(){//Pythagoras here!
    return Math.sqrt(x*x+y*y);
  }

  public Coord toCoord(){
    return new Coord((int)(x-0.5d), (int)(y-0.5d));
  }
}