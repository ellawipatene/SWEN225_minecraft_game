package main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import imgs.Img;

class Cells{
  int maxX=16;
  int maxY=16;
  private final List<List<Cell>> inner=new ArrayList<>();
  public Cells(){
    for(int x=0;x<maxX;x++){
      var tmp=new ArrayList<Cell>();
      inner.add(tmp);
      for(int y=0;y<maxY;y++){
        tmp.add(new Cell(List.of(Img.Grass,Img.Tree),x,y));
      }
    }
    inner.get(3).set(3,new Cell(List.of(Img.Grass),3,3));//a patch of grass
    inner.get(4).set(4,new Cell(List.of(Img.Grass),4,4));
    inner.get(3).set(4,new Cell(List.of(Img.Grass),3,4));
  }
  public Cell get(int x,int y){
    var isOut = x<0 || y<0 || x>=maxX || y>=maxY;
    if(isOut){ return new Cell(List.of(Img.Rock),x,y); }
    var res = inner.get(x).get(y);
    assert res!=null;
    return res;
  }
  public void forAll(Coord p,int range,Consumer<Cell>action){
    assert range>=0;
    for(int x=p.x()-range;x<=p.x()+range;x++){
      for(int y=p.y()-range;y<=p.y()+range;y++){
        action.accept(get(x,y));
      }
    }
  }
}