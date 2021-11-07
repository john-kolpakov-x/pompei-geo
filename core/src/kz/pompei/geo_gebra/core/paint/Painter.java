package kz.pompei.geo_gebra.core.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;
import kz.pompei.geo_gebra.core.draw.DrawArrow;
import kz.pompei.geo_gebra.core.filter.EventFilter;

public class Painter extends JPanel {
  private final Drawer drawer;

  private boolean moveOperation = false;
  private Point   from, to;

  public Painter(Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    drawer.draw((Graphics2D) g, getSize());
    paintMoveOperation((Graphics2D) g);
  }

  private void paintMoveOperation(Graphics2D g) {
    if (!moveOperation) return;

    g.setColor(Color.BLACK);
    DrawArrow.drawArrow(g, from, to, 10, 45.0);
  }

  public void mouseEvent(MouseEvent e) {
    if (EventFilter.mousePressed().middle().does(e)) {
      moveOperation = true;
      from          = e.getPoint();
      to            = from;
      repaint();
      return;
    }

    if (EventFilter.mousePressed().right().does(e)) {
      moveOperation = false;
      repaint();
      return;
    }

    if (EventFilter.mouseDragged().any().does(e)) {
      to = new Point(e.getX(), e.getY());
      repaint();
      return;
    }

    if (EventFilter.mouseReleased().middle().altPressed().does(e)) {
      if (moveOperation) {
        int deltaX = to.x - from.x;
        int deltaY = to.y - from.y;
        drawer.moveAxes(deltaX, deltaY);
        moveOperation = false;
        repaint();
      }
      return;
    }
  }

  public void mouseWheelEvent(MouseWheelEvent e) {
    double rescaleFactor = 1.1;
    drawer.rescale(e.getPoint(), getSize(), e.getWheelRotation() < 0 ? rescaleFactor : 1 / rescaleFactor);
    repaint();
  }

  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 1) {
      drawer.printPointInfo(e.getPoint(), getSize());
    }
  }

  public void resetMapper() {
    drawer.resetMapper();
    repaint();
  }
}
