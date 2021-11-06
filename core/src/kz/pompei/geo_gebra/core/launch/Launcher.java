package kz.pompei.geo_gebra.core.launch;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kz.pompei.geo_gebra.core.paint.Drawer;
import kz.pompei.geo_gebra.core.paint.MouseEventType;
import kz.pompei.geo_gebra.core.paint.Painter;

public class Launcher {

  public static void main(String[] args) {
    new Launcher().exec();
  }

  private void exec() {
    JFrame frame = new JFrame();
    frame.setTitle("Pompei Geo-Geb Ra");

    frame.setLocation(4000, 100);
    frame.setSize(1200, 600);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    Painter painter = new Painter(new Drawer());

    frame.setContentPane(painter);

    painter.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        painter.mouseEvent(MouseEventType.MOUSE_PRESSED, e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        painter.mouseEvent(MouseEventType.MOUSE_RELEASED, e);
      }
    });
    painter.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        painter.mouseEvent(MouseEventType.MOUSE_DRAGGED, e);
      }
    });

    frame.setVisible(true);
  }

}
