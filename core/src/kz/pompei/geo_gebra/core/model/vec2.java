package kz.pompei.geo_gebra.core.model;

import java.awt.Dimension;
import java.awt.Point;

public record vec2(double x, double y) {
  public static vec2 of(double x, double y) {
    return new vec2(x, y);
  }

  public static vec2 zero() {
    return of(0, 0);
  }

  public static vec2 from(Point point) {
    return point == null ? zero() : of(point.x, point.y);
  }

  public static vec2 from(Dimension dimension) {
    return dimension == null ? zero() : of(dimension.width, dimension.height);
  }

  public Point toPoint() {
    return new Point((int) Math.round(x), (int) Math.round(y));
  }

  public vec2 plus(vec2 a) {
    return vec2.of(x + a.x, y + a.y);
  }

  public vec2 plus(double ax, double ay) {
    return vec2.of(x + ax, y + ay);
  }

  public vec2 mul(vec2 a) {
    return vec2.of(x * a.x, y * a.y);
  }

  public vec2 mul(double ax, double ay) {
    return vec2.of(x * ax, y * ay);
  }

  public vec2 mul(double a) {
    return mul(a, a);
  }

  public vec2 div(vec2 a) {
    return vec2.of(x / a.x, y / a.y);
  }

  public vec2 div(double ax, double ay) {
    return vec2.of(x / ax, y / ay);
  }

  public vec2 div(double a) {
    return div(a, a);
  }

  public vec2 minus(vec2 a) {
    return vec2.of(x - a.x, y - a.y);
  }

  public vec2 minus(double ax, double ay) {
    return vec2.of(x - ax, y - ay);
  }

  public double scalar(vec2 a) {
    return x * a.x + y * a.y;
  }

  public vec2 scalar(vec2 ox, vec2 oy) {
    return vec2.of(scalar(ox), scalar(oy));
  }

  public vec2 unScalar(vec2 ox, vec2 oy) {
    return ox.mul(x).plus(oy.mul(y));
  }

  public double vec(vec2 a) {
    return x * a.y - y * a.x;
  }

  public vec2 minus() {
    return vec2.of(-x, -y);
  }
}
