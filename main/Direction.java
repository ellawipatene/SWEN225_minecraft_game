package main;

enum Direction{
  None(0d,0d){},
  Up(0d,-1d){ 
    Direction right(){return UpRight;}
    Direction left(){return UpLeft;}
    Direction unUp(){return None;}
  },
  UpRight(+0.7071d,-0.7071d){
    Direction up(){return this;}
    Direction right(){return this;}
    Direction unUp(){return Right;}
    Direction unRight(){return Up;}
  },
  Right(+1d,0d){
    Direction up(){return UpRight;}
    Direction down(){return DownRight;}
    Direction unRight(){return None;}
  },
  DownRight(+0.7071d,+0.7071d){
    Direction right(){return this;}
    Direction down(){return this;}
    Direction unDown(){return Right;}
    Direction unRight(){return Down;}
  },
  Down(0d,+1d){
    Direction right(){return DownRight;}
    Direction left(){return DownLeft;}
    Direction unDown(){return None;}
  },
  DownLeft(-0.7071d,+0.7071d){
    Direction down(){return this;}
    Direction left(){return this;}
    Direction unDown(){return Left;}
    Direction unLeft(){return Down;}
  },
  Left(-1d,0d){
    Direction up(){return UpLeft;}
    Direction down(){return DownLeft;}
    Direction unLeft(){return None;}
  },
  UpLeft(-0.7071d,-0.7071d){
    Direction up(){return this;}
    Direction left(){return this;}
    Direction unUp(){return Left;}
    Direction unLeft(){return Up;}
  };
  public final Point arrow;
  Direction up(){return Up;}
  Direction right(){return Right;}
  Direction down(){return Down;}
  Direction left(){return Left;}
  Direction unUp(){return this;}
  Direction unRight(){return this;}
  Direction unDown(){return this;}
  Direction unLeft(){return this;}
  Point arrow(Double speed){ return arrow.times(speed,speed);}
  Direction(double x,double y){ arrow=new Point(x,y); }
}