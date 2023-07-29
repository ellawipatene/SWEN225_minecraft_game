package main;

import java.awt.event.KeyEvent;

class Controller extends Keys{
  Controller(Camera c,Sword s, char[] keyCodes){ 
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[0]) ,c.set(Direction::up),c.set(Direction::unUp));
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[1]),c.set(Direction::down),c.set(Direction::unDown));
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[2]),c.set(Direction::left),c.set(Direction::unLeft));
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[3]),c.set(Direction::right),c.set(Direction::unRight));
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[4]),s.set(Direction::left),s.set(Direction::unLeft));
	  setAction(KeyEvent.getExtendedKeyCodeForChar(keyCodes[5]),s.set(Direction::right),s.set(Direction::unRight));
  }
}