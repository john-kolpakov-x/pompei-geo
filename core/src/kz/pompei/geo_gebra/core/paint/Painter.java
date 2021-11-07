package kz.pompei.geo_gebra.core.paint;

import kz.pompei.geo_gebra.core.model.vec2;

public interface Painter {

  Painter moveAt(vec2 point);

  Painter moveTo(vec2 delta);

  Painter lineAt(vec2 point);

  Painter lineTo(vec2 delta);

}
