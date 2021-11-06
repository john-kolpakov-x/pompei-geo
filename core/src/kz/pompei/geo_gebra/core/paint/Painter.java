package kz.pompei.geo_gebra.core.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import kz.pompei.geo_gebra.core.draw.DrawArrow;

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
    drawer.draw((Graphics2D) g, getWidth(), getHeight());
    paintMoveOperation((Graphics2D) g);
  }

  private void paintMoveOperation(Graphics2D g) {
    if (!moveOperation) return;

    g.setColor(Color.BLACK);
    DrawArrow.drawArrow(g, from, to, 10, 45.0);
  }

  public void mouseEvent(MouseEventType met, MouseEvent e) {
    if (met == MouseEventType.MOUSE_PRESSED && e.getButton() == MouseEvent.BUTTON1) {
      moveOperation = true;
      from          = e.getPoint();
      to            = from;
      repaint();
      return;
    }
    if (met == MouseEventType.MOUSE_PRESSED && e.getButton() == MouseEvent.BUTTON3) {
      moveOperation = false;
      repaint();
      return;
    }

    if (met == MouseEventType.MOUSE_DRAGGED) {
      to = new Point(e.getX(), e.getY());
      repaint();
      return;
    }

    if (met == MouseEventType.MOUSE_RELEASED && e.getButton() == MouseEvent.BUTTON1) {
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

}
