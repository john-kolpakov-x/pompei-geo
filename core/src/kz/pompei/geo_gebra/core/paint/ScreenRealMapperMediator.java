package kz.pompei.geo_gebra.core.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import kz.pompei.geo_gebra.core.align.FloatAlign;
import kz.pompei.geo_gebra.core.draw.DrawArrow;
import kz.pompei.geo_gebra.core.model.ScreenRealMapper;
import kz.pompei.geo_gebra.core.model.vec2;
import kz.pompei.geo_gebra.core.store.StrAcceptor;

public class ScreenRealMapperMediator {

  private final StrAcceptor pixelWorldMapperSaver;

  private static final Color AXES_COLOR = Color.gray;

  private final ScreenRealMapper mapper = new ScreenRealMapper();

  public ScreenRealMapperMediator(StrAcceptor pixelWorldMapperSaver) {
    this.pixelWorldMapperSaver = pixelWorldMapperSaver;
    mapper.assign(ScreenRealMapper.deserialize(pixelWorldMapperSaver.get()));
  }

  public void draw(Graphics2D g, Dimension size) {
    drawAxes(g, size);
  }

  private void drawAxes(Graphics2D g, Dimension size) {
    drawAxeX(g, size);
    drawAxeY(g, size);
  }

  private void drawAxeX(Graphics2D g, Dimension size) {

    double width_2 = (double) size.width / 2.0;

    double pixelDeltaCenterX = mapper.xCenter * mapper.scaleX;
    if (pixelDeltaCenterX > width_2) {
      return;
    }
    if (pixelDeltaCenterX < -width_2) {
      return;
    }

    int x = (int) Math.round(width_2 + pixelDeltaCenterX);

    g.setColor(AXES_COLOR);
    DrawArrow.drawArrow(g, x, size.height, x, 0, 10, 20);
  }

  private void drawAxeY(Graphics2D g, Dimension size) {
    double height_2 = (double) size.height / 2.0;

    double pixelDeltaCenterY = mapper.yCenter * mapper.scaleY;
    if (pixelDeltaCenterY > height_2) return;
    if (pixelDeltaCenterY < -height_2) return;

    int y = (int) Math.round(height_2 - pixelDeltaCenterY);

    g.setColor(AXES_COLOR);
    DrawArrow.drawArrow(g, 0, y, size.width, y, 10, 20);
  }


  @SuppressWarnings("UnnecessaryLocalVariable")
  public void moveAxes(int pixelDeltaX, int pixelDeltaY) {
    {
      double px = pixelDeltaX;
      double py = pixelDeltaY;
      double p  = Math.sqrt(px * px + py * py);
      if (p < 5) return;
    }

    double deltaX = +(pixelDeltaX / mapper.scaleX);
    double deltaY = -(pixelDeltaY / mapper.scaleY);

    mapper.xCenter += deltaX;
    mapper.yCenter += deltaY;
    saveMapper();
  }

  public void rescale(Point point, Dimension size, double rescaleFactor) {
    mapper.rescale(point, size, rescaleFactor);
    saveMapper();
  }

  private void saveMapper() {
    pixelWorldMapperSaver.set(mapper.serialize());
  }

  public void printPointInfo(Point point, Dimension size) {
    var real   = mapper.toReal(point, size);
    var screen = mapper.toScreen(real, size);
    var half   = vec2.from(size).div(2);
    var ax     = new FloatAlign();
    var ay     = new FloatAlign();
    ax.append(point.x);
    ax.append(real.x());
    ax.append(mapper.xCenter);
    ax.append(screen.x());
    ax.append(half.x());
    ax.append(mapper.scaleX);
    ay.append(point.y);
    ay.append(real.y());
    ay.append(mapper.yCenter);
    ay.append(screen.y());
    ay.append(half.y());
    ay.append(mapper.scaleY);
    System.out.println("8SZhvJC1P0 :: CLICKED TO : (" + ax.display(point.x) + ", " + ay.display(point.y) + ")");
    System.out.println("                    half : (" + ax.display(half.x()) + ", " + ay.display(half.y()) + ")");
    System.out.println("                    real : (" + ax.display(real.x()) + ", " + ay.display(real.y()) + ")");
    System.out.println("                  screen : (" + ax.display(screen.x()) + ", " + ay.display(screen.y()) + ")");
    System.out.println();
    System.out.println("                  center : (" + ax.display(mapper.xCenter) + ", " + ay.display(mapper.yCenter) + ")");
    System.out.println("                   scale : (" + ax.display(mapper.scaleX) + ", " + ay.display(mapper.scaleY) + ")");
    System.out.println();
    System.out.println();
  }

  public void resetMapper() {
    mapper.reset();
    saveMapper();
  }
}
