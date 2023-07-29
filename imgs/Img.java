package imgs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public enum Img{
  AwakeMonster,
  DeadMonster,
  SleepMonster,
  Grass,
  Hero,
  Tree,
  Sword,
  Rock;
  public final BufferedImage image;
  Img(){image=loadImage(this.name());}
  static private BufferedImage loadImage(String name){
    URL imagePath = Img.class.getResource(name+".png");
    try{return ImageIO.read(imagePath);}
    catch(IOException e) { throw new Error(e); }
  }
}