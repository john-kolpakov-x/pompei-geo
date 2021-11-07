package kz.pompei.geo_gebra.core.model;

import java.awt.Dimension;
import java.awt.Point;

public class ScreenRealMapper {
  public double xCenter = 0, yCenter = 0, scaleX = 1, scaleY = 1;

  public vec2 center() {
    return vec2.of(xCenter, yCenter);
  }

  public vec2 scale() {
    return vec2.of(scaleX, scaleY);
  }

  public void assign(ScreenRealMapper a) {
    xCenter = a.xCenter;
    yCenter = a.yCenter;
    scaleX  = a.scaleX;
    scaleY  = a.scaleY;
  }

  public ScreenRealMapper() {
    reset();
  }

  public void reset() {
    xCenter = 0;
    yCenter = 0;
    scaleX  = 1;
    scaleY  = 1;
  }

  public String serialize() {
    return xCenter + " " + yCenter + " " + scaleX + " " + scaleY;
  }

  public static ScreenRealMapper deserialize(String str) {
    if (str == null) {
      return new ScreenRealMapper();
    }

    var split = str.trim().split("\\s+");
    if (split.length != 4) return new ScreenRealMapper();

    try {
      var rete = new ScreenRealMapper();
      rete.xCenter = Double.parseDouble(split[0]);
      rete.yCenter = Double.parseDouble(split[1]);
      rete.scaleX  = Double.parseDouble(split[2]);
      rete.scaleY  = Double.parseDouble(split[3]);
      return rete;
    } catch (NumberFormatException ignore) {
      return new ScreenRealMapper();
    }

  }

  @Override
  public String toString() {
    return "PixelWorldMapper{" +
             "Center=(" + xCenter + ", " + yCenter + ")" +
             ", scale=(" + scaleX + ", " + scaleY + ")" +
             '}';
  }

  public void rescale(Point point, Dimension size, double rescaleFactor) {

    var newScale = scale().mul(rescaleFactor);

    var real   = toReal(point, size);
    var screen = toScreen(real, size);

    var newCenter = screen.mul(1, -1)
                          .plus(vec2.from(size).mul(-1, 1).div(2))
                          .div(newScale)
                          .minus(real);

    scaleX  = newScale.x();
    scaleY  = newScale.y();
    xCenter = newCenter.x();
    yCenter = newCenter.y();
  }

  public vec2 toReal(Point point, Dimension size) {
    return vec2.from(point)
               .mul(1, -1)
               .plus(vec2.from(size).mul(-1, 1).div(2))
               .div(scale())
               .minus(center());
  }

  public vec2 toScreen(vec2 real, Dimension size) {
    return center().plus(real)
                   .mul(scale())
                   .mul(1, -1)
                   .plus(vec2.from(size).div(2));
  }
}
