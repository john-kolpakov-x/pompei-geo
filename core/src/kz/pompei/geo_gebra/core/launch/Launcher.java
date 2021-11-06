package kz.pompei.geo_gebra.core.launch;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import kz.pompei.geo_gebra.core.paint.Drawer;
import kz.pompei.geo_gebra.core.paint.Painter;

public class Launcher {

  public static void main(String[] args) {
    new Launcher().exec();
  }

  private void exec() {
    JFrame frame = new JFrame();
    frame.setTitle("Pompei Geo-Geb Ra");

    frame.setLocation(4000,100);
    frame.setSize(1200, 600);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    frame.setContentPane(new Painter(new Drawer()));

    frame.setVisible(true);
  }

}
