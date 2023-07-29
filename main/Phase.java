package main;

import java.util.ArrayList;
import java.util.List;

import imgs.Img;

record Phase(Model model, Controller controller){ 
  static Phase level1(Runnable next, Runnable first, char[] keyCodes, int levelNum) {
    Camera c = new Camera(new Point(5,5));
    Sword s = new Sword(c);
    Cells cells = new Cells();
    Monster bossMon = new Monster(new Point(0,0), new RoamingState());
    List<List<Entity>> lists = List.of(List.of(c,s,new Monster(new Point(0,0))), 
    		List.of(c,s,new Monster(new Point(0,0)), new Monster(new Point(0,16)), new Monster(new Point(16, 0)), new Monster(new Point(16,16), new RoamingState())),
    		List.of(c,s, bossMon ,new Sword(bossMon) {
    			public double distance(){ return 1.5d;}
    			public double speed(){ return 0.4d;}
    			public Direction getDir() {return Direction.Left;}
        }));
    var m = new Model(){
      List<Entity> entities = lists.get(levelNum);
      public Camera camera(){ return c; }
      public void addEntity(Entity e) {entities.add(e);}
      public List<Entity> entities(){ return entities; }
      public void remove(Entity e){ 
        entities = entities.stream()
          .filter(ei->!ei.equals(e))
          .toList();
      }
      public Cells cells(){ return cells; }
      public void onGameOver(){ first.run(); }
      public void onNextLevel(){ next.run(); }
    };
    return new Phase(m,new Controller(c,s, keyCodes)); 
  }
}