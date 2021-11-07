package kz.pompei.geo_gebra.core.model;

public record sector2(vec2 start, vec2 finish, boolean infiniteBefore, boolean infiniteAfter) {

  public vec2 delta() {
    return finish.minus(start);
  }

  public static sector2 of(vec2 start, vec2 finish) {
    return new sector2(start, finish, false, false);
  }

  public static sector2 ray(vec2 start, vec2 finish) {
    return new sector2(start, finish, false, true);
  }

  public static sector2 rayReverse(vec2 start, vec2 finish) {
    return new sector2(start, finish, true, false);
  }

  public static sector2 line(vec2 start, vec2 finish) {
    return new sector2(start, finish, true, true);
  }

  public sector2 flip() {
    return new sector2(finish, start, infiniteAfter, infiniteBefore);
  }

}
