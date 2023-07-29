package main;

record Coord(int x, int y){
  public Point toPoint(){
    return new Point(x+0.5d, y+0.5d);
  }
}