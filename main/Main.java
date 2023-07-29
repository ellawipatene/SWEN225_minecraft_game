package main;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[]a){
    SwingUtilities.invokeLater(Compact::new);
  }
}