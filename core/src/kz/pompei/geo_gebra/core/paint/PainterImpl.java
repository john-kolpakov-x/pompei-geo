package kz.pompei.geo_gebra.core.paint;

import java.awt.Dimension;
import java.awt.Graphics2D;
import kz.pompei.geo_gebra.core.model.ScreenRealMapper;
import kz.pompei.geo_gebra.core.model.vec2;

public class PainterImpl implements Painter {
  private final Graphics2D       g;
  private final ScreenRealMapper mapper;
  private final Dimension        size;

  private vec2 cursor = vec2.zero();

  public PainterImpl(Graphics2D g, ScreenRealMapper mapper, Dimension size) {
    this.g      = g;
    this.mapper = mapper;
    this.size   = size;
  }

  @Override
  public Painter moveAt(vec2 point) {
    cursor = point == null ? vec2.zero() : point;
    return this;
  }

  @Override
  public Painter moveTo(vec2 delta) {
    if (delta != null) {
      cursor = cursor.plus(delta);
    }
    return this;
  }

  @Override
  public Painter lineAt(vec2 point) {
    vec2 start = cursor;
    moveAt(point);
    drawLine(start, cursor);
    return this;
  }

  @Override
  public Painter lineTo(vec2 delta) {
    vec2 start = cursor;
    moveTo(delta);
    drawLine(start, cursor);
    return this;
  }

  private void drawLine(vec2 start, vec2 end) {

  }

}
