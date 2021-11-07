package kz.pompei.geo_gebra.core.model;

import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

public class CutTest {

  @Test
  public void sectorByHalfPlaneLeft_001() {

    var sector         = sector2.of(vec2.zero(), vec2.zero());
    var planeStart     = vec2.zero();
    var planeDirection = vec2.zero();

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();

  }

  @Test
  public void sectorByHalfPlaneLeft_002() {

    var sector         = sector2.of(vec2.of(1, 1), vec2.of(2, 2));
    var planeStart     = vec2.zero();
    var planeDirection = vec2.zero();

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();

  }

  @Test
  public void sectorByHalfPlaneLeft_003() {

    var sector         = sector2.of(vec2.of(1, 1), vec2.of(2, 2));
    var planeStart     = vec2.of(0, 0);
    var planeDirection = vec2.of(1, 0);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isSameInstanceAs(sector);

  }

  @Test
  public void sectorByHalfPlaneLeft_004() {

    var sector         = sector2.of(vec2.of(1, 1), vec2.of(2, 2));
    var planeStart     = vec2.of(0, 0);
    var planeDirection = vec2.of(1, -1);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isSameInstanceAs(sector);

  }

  @Test
  public void sectorByHalfPlaneLeft_005() {

    var sector         = sector2.of(vec2.of(8, 8), vec2.of(6, 11));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isSameInstanceAs(sector);

  }

  @Test
  public void sectorByHalfPlaneLeft_006() {

    var sector         = sector2.ray(vec2.of(8, 8), vec2.of(6, 11));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isSameInstanceAs(sector);

  }

  @Test
  public void sectorByHalfPlaneLeft_007() {

    var sector         = sector2.rayReverse(vec2.of(8, 8), vec2.of(6, 11));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(8.615);
    assertThat(result.start().y()).isWithin(0.01).of(7.077);
    assertThat(result.finish().x()).isWithin(0.01).of(6);
    assertThat(result.finish().y()).isWithin(0.01).of(11);
  }

  @Test
  public void sectorByHalfPlaneLeft_008() {

    var sector         = sector2.line(vec2.of(2, 4), vec2.of(4, 8));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isTrue();
    assertThat(result.start().x()).isWithin(0.01).of(1);
    assertThat(result.start().y()).isWithin(0.01).of(2);
    assertThat(result.finish().x()).isWithin(0.01).of(4);
    assertThat(result.finish().y()).isWithin(0.01).of(8);
  }

  @Test
  public void sectorByHalfPlaneLeft_009() {

    var sector         = sector2.line(vec2.of(4, 11), vec2.of(5, 7));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isTrue();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(4);
    assertThat(result.start().y()).isWithin(0.01).of(11);
    assertThat(result.finish().x()).isWithin(0.01).of(5.5);
    assertThat(result.finish().y()).isWithin(0.01).of(5);
  }

  @Test
  public void sectorByHalfPlaneLeft_010() {

    var sector         = sector2.ray(vec2.of(4, 11), vec2.of(5, 7));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(4);
    assertThat(result.start().y()).isWithin(0.01).of(11);
    assertThat(result.finish().x()).isWithin(0.01).of(5.5);
    assertThat(result.finish().y()).isWithin(0.01).of(5);
  }

  @Test
  public void sectorByHalfPlaneLeft_011() {

    var sector         = sector2.of(vec2.of(4, 11), vec2.of(5, 7));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isSameInstanceAs(sector);
  }

  @Test
  public void sectorByHalfPlaneLeft_012() {

    var sector         = sector2.of(vec2.of(4, 7), vec2.of(7, 9));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isSameInstanceAs(sector);
  }

  @Test
  public void sectorByHalfPlaneLeft_013() {

    var sector         = sector2.of(vec2.of(10, 9), vec2.of(7, 5));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(10);
    assertThat(result.start().y()).isWithin(0.01).of(9);
    assertThat(result.finish().x()).isWithin(0.01).of(8.5);
    assertThat(result.finish().y()).isWithin(0.01).of(7);
  }

  @Test
  public void sectorByHalfPlaneLeft_014() {

    var sector         = sector2.of(vec2.of(11, 6), vec2.of(14, 8));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();
  }

  @Test
  public void sectorByHalfPlaneLeft_015() {

    var sector         = sector2.of(vec2.of(7, 2), vec2.of(9, 6));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();
  }

  @Test
  public void sectorByHalfPlaneLeft_016() {

    var sector         = sector2.rayReverse(vec2.of(7, 2), vec2.of(9, 6));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();
  }

  @Test
  public void sectorByHalfPlaneLeft_017() {

    var sector         = sector2.ray(vec2.of(9, 6), vec2.of(7, 2));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNull();
  }

  @Test
  public void sectorByHalfPlaneLeft_018() {

    var sector         = sector2.line(vec2.of(7, 2), vec2.of(9, 6));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isTrue();
    assertThat(result.start().x()).isWithin(0.01).of(10);
    assertThat(result.start().y()).isWithin(0.01).of(8);
    assertThat(result.finish().x()).isWithin(0.01).of(12);
    assertThat(result.finish().y()).isWithin(0.01).of(12);
  }

  @Test
  public void sectorByHalfPlaneLeft_019() {

    var sector         = sector2.ray(vec2.of(7, 2), vec2.of(9, 6));
    var planeStart     = vec2.of(4, 4);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isTrue();
    assertThat(result.start().x()).isWithin(0.01).of(10);
    assertThat(result.start().y()).isWithin(0.01).of(8);
    assertThat(result.finish().x()).isWithin(0.01).of(12);
    assertThat(result.finish().y()).isWithin(0.01).of(12);
  }

  @Test
  public void sectorByHalfPlaneLeft_020() {

    var sector         = sector2.of(vec2.of(6, 4), vec2.of(-1, 2));
    var planeStart     = vec2.of(1, 2);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(2.5);
    assertThat(result.start().y()).isWithin(0.01).of(3);
    assertThat(result.finish().x()).isWithin(0.01).of(-1);
    assertThat(result.finish().y()).isWithin(0.01).of(2);
  }

  @Test
  public void sectorByHalfPlaneLeft_021() {

    var sector         = sector2.line(vec2.of(6, 4), vec2.of(-1, 2));
    var planeStart     = vec2.of(1, 2);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isFalse();
    assertThat(result.infiniteAfter()).isTrue();
    assertThat(result.start().x()).isWithin(0.01).of(2.5);
    assertThat(result.start().y()).isWithin(0.01).of(3);
    assertThat(result.finish().x()).isWithin(0.01).of(-1);
    assertThat(result.finish().y()).isWithin(0.01).of(2);
  }

  @Test
  public void sectorByHalfPlaneLeft_022() {

    var sector         = sector2.line(vec2.of(11, 6), vec2.of(13, 2));
    var planeStart     = vec2.of(1, 2);
    var planeDirection = vec2.of(3, 2);

    //
    //
    var result = Cut.sectorByHalfPlaneLeft(sector, planeStart, planeDirection);
    //
    //

    assertThat(result).isNotNull();
    assertThat(result).isNotSameInstanceAs(sector);
    assertThat(result.infiniteBefore()).isTrue();
    assertThat(result.infiniteAfter()).isFalse();
    assertThat(result.start().x()).isWithin(0.01).of(8);
    assertThat(result.start().y()).isWithin(0.01).of(12);
    assertThat(result.finish().x()).isWithin(0.01).of(10);
    assertThat(result.finish().y()).isWithin(0.01).of(8);
  }

}
