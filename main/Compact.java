package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class Compact extends JFrame{
  private static final long serialVersionUID = 1L;
  Runnable closePhase = ()->{};
  Phase currentPhase;
  private String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
  private String[] labelText = {"Up Key:", "Down Key:", "Right Key:", "Left Key:", "Sword Left Key:", "Sword Right Key:"};
  private int[] keys = {22,18,0,3,14,15}; 
  Compact(){
    assert SwingUtilities.isEventDispatchThread();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    phaseZero();
    setVisible(true);
    addWindowListener(new WindowAdapter( ){
      public void windowClosed(WindowEvent e){closePhase.run();}
    });
  }
  private void phaseZero() {
    var welcome=new JLabel("Welcome to Compact. A compact Java game!");
    var start=new JButton("Start!");
    ArrayList<JComboBox> keyOptions = new ArrayList<JComboBox>(); 
    ArrayList<JLabel> labels = new ArrayList<JLabel>(); 
    for(int i = 0; i < 6; i++) {
    	keyOptions.add(new JComboBox(chars));
    	labels.add(new JLabel(labelText[i]));
    	keyOptions.get(i).setSelectedIndex(keys[i]);
    	keyOptions.get(i).setBounds(130 * i, 50, 100, 50);
    	labels.get(i).setBounds(130 * i + 5, 25, 130, 50);
    	add(BorderLayout.CENTER, labels.get(i)); 
    	add(BorderLayout.CENTER, keyOptions.get(i));
    }
    
    for(JComboBox box : keyOptions){box.addActionListener(e -> keys[keyOptions.indexOf(box)] = Arrays.asList(chars).indexOf(box.getSelectedItem()));}
    closePhase.run();
    closePhase=()->{
     remove(welcome);
     remove(start);
     labels.forEach(e -> remove(e));
     keyOptions.forEach(e -> remove(e));
     };
    add(BorderLayout.CENTER,welcome);
    add(BorderLayout.SOUTH,start);
    start.addActionListener(e->phaseOne());
    setPreferredSize(new Dimension(800,400));
    pack();
  }
  private void phaseOne(){setPhase(Phase.level1(()->phaseTwo(),()->phaseZero(), (chars[keys[0]]+chars[keys[1]]+chars[keys[2]]+chars[keys[3]]+chars[keys[4]]+chars[keys[5]]).toCharArray(), 0));}
  private void phaseTwo(){setPhase(Phase.level1(()->phaseThree(),()->phaseZero(), (chars[keys[0]]+chars[keys[1]]+chars[keys[2]]+chars[keys[3]]+chars[keys[4]]+chars[keys[5]]).toCharArray(), 1));}
  private void phaseThree(){setPhase(Phase.level1(()->phaseFour(),()->phaseZero(), (chars[keys[0]]+chars[keys[1]]+chars[keys[2]]+chars[keys[3]]+chars[keys[4]]+chars[keys[5]]).toCharArray(), 2));}
  private void phaseFour(){
	add(BorderLayout.CENTER , new JLabel("Congrats! You have won the game."));
	closePhase.run();
	pack();
  }
  void setPhase(Phase p){  //set up the viewport and the timer
    Viewport v = new Viewport(p.model());
    v.addKeyListener(p.controller());
    v.setFocusable(true);
    Timer timer = new Timer(34,unused->{
      assert SwingUtilities.isEventDispatchThread();
      p.model().ping();
      v.repaint();
    });
    closePhase.run();//close phase before adding any element of the new phase
    closePhase=()->{ timer.stop(); remove(v); };
    add(BorderLayout.CENTER,v);//add the new phase viewport
    setPreferredSize(getSize());//to keep the current size
    pack();                     //after pack
    v.requestFocus();//need to be after pack
    timer.start();
  }
}