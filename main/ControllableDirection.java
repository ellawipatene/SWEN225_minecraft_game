package main;

import java.util.function.Function;

abstract class ControllableDirection{
  private Direction direction=Direction.None;
  public Direction direction(){ return direction; }
  public void direction(Direction d){ direction=d; }
  public Runnable set(Function<Direction,Direction> f){
    return ()->direction=f.apply(direction);
  }
  public void set(Direction d) {
	  direction = d; 
  }
}