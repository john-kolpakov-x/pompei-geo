package kz.pompei.geo_gebra.core.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public class Drawer {

  private static final Color AXES_COLOR = Color.gray;

  double xCenter = 0, yCenter = 0, scaleX = 1, scaleY = 1;

  public void draw(Graphics2D g, int width, int height) {
    drawAxes(g, width, height);
  }

  private void drawAxes(Graphics2D g, int width, int height) {
    drawAxeX(g, width, height);
    drawAxeY(g, width, height);
  }

  private void drawAxeX(Graphics2D g, int width, int height) {

    double width_2 = (double) width / 2.0;

    double pixelDeltaCenterX = xCenter / scaleX;
    if (pixelDeltaCenterX > width_2) {
      return;
    }
    if (pixelDeltaCenterX < -width_2) {
      return;
    }

    int x = (int) Math.round(width_2 + pixelDeltaCenterX);

    g.setColor(AXES_COLOR);
    g.drawLine(x, 0, x, height);
  }

  private void drawAxeY(Graphics2D g, int width, int height) {
    double height_2 = (double) height/2.0;

    double pixelDeltaCenterY = yCenter/scaleY;
    if (pixelDeltaCenterY > height_2)return;
    if (pixelDeltaCenterY < -height_2)return;

    int y = (int)Math.round(height_2 - pixelDeltaCenterY);

    g.setColor(AXES_COLOR);
    g.drawLine(0, y, width, y);

  }

}
