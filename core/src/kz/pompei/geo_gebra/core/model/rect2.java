package kz.pompei.geo_gebra.core.model;

public record rect2(vec2 start, vec2 ox, vec2 oy) {

  public sector2 cut(sector2 source) {
    return Cut.sectorByRect(source, this);
  }

}
