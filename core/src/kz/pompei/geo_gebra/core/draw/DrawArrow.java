package kz.pompei.geo_gebra.core.draw;

import java.awt.Graphics2D;
import java.awt.Point;

public class DrawArrow {

  public Point  from;
  public Point  to;
  public int    size;
  public double angle;

  public static void drawArrow(Graphics2D g, Point from, Point to, int size, double angle) {
    var drawArrow = new DrawArrow();
    drawArrow.from  = from;
    drawArrow.to    = to;
    drawArrow.size  = size;
    drawArrow.angle = angle;
    drawArrow.draw(g);
  }

  public static void drawArrow(Graphics2D g, int x1, int y1, int x2, int y2, int size, double angle) {
    var drawArrow = new DrawArrow();
    drawArrow.from  = new Point(x1, y1);
    drawArrow.to    = new Point(x2, y2);
    drawArrow.size  = size;
    drawArrow.angle = angle;
    drawArrow.draw(g);
  }

  private void draw(Graphics2D g) {
    g.drawLine(from.x, from.y, to.x, to.y);

    double angle = this.angle * Math.PI / 180.0;

    drawArrowSide(g, +angle);
    drawArrowSide(g, -angle);
  }

  @SuppressWarnings("UnnecessaryLocalVariable")
  private void drawArrowSide(Graphics2D g, double angle) {
    int pixelDeltaX = to.x - from.x;
    int pixelDeltaY = to.y - from.y;
    if (pixelDeltaX == 0 && pixelDeltaY == 0) return;

    double deltaX = pixelDeltaX;
    double deltaY = pixelDeltaY;

    double delta = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

    double ix = -deltaX / delta, iy = -deltaY / delta;
    double jx = -iy, jy = ix;

    double size = this.size;

    {
      double ax = size * (ix * Math.cos(angle) + jx * Math.sin(angle));
      double ay = size * (iy * Math.cos(angle) + jy * Math.sin(angle));
      int    Ax = (int) Math.round(ax), Ay = (int) Math.round(ay);

      g.drawLine(to.x, to.y, to.x + Ax, to.y + Ay);
    }
  }

}
