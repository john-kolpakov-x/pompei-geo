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
}
