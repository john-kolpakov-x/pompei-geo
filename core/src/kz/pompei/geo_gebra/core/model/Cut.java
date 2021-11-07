package kz.pompei.geo_gebra.core.model;

public class Cut {
  @SuppressWarnings("UnnecessaryLocalVariable")
  public static sector2 sectorByRect(sector2 sector, rect2 rect) {

    if (sector == null) return null;

    double norm = rect.ox().vec(rect.oy());

    if (norm > 0) {
      sector2 sector2 = sectorByHalfPlaneLeft(sector, rect.start(), rect.ox());
      sector2 sector3 = sectorByHalfPlaneLeft(sector2, rect.start().plus(rect.ox()), rect.oy());
      sector2 sector4 = sectorByHalfPlaneRight(sector3, rect.start(), rect.oy());
      sector2 sector5 = sectorByHalfPlaneRight(sector4, rect.start().plus(rect.oy()), rect.ox());
      return sector5;
    }

    if (norm < 0) {
      sector2 sector2 = sectorByHalfPlaneRight(sector, rect.start(), rect.ox());
      sector2 sector3 = sectorByHalfPlaneRight(sector2, rect.start().plus(rect.ox()), rect.oy());
      sector2 sector4 = sectorByHalfPlaneLeft(sector3, rect.start(), rect.oy());
      sector2 sector5 = sectorByHalfPlaneLeft(sector4, rect.start().plus(rect.oy()), rect.ox());
      return sector5;
    }

    return null;
  }

  public static sector2 sectorByHalfPlaneRight(sector2 sector, vec2 planeStart, vec2 planeDirection) {
    return sectorByHalfPlaneLeft(sector, planeStart, planeDirection.minus());
  }

  public static sector2 sectorByHalfPlaneLeft(sector2 sector, vec2 planeStart, vec2 planeDirection) {
    if (sector == null) return null;

    //noinspection UnnecessaryLocalVariable
    vec2 ox = planeDirection;
    vec2 oy = vec2.of(-ox.y(), ox.x());

    var start_          = sector.start().minus(planeStart).scalar(ox, oy);
    var finish_         = sector.finish().minus(planeStart).scalar(ox, oy);
    var infiniteBefore_ = sector.infiniteBefore();
    var infiniteAfter_  = sector.infiniteAfter();
    var delta_          = finish_.minus(start_);

    if (false
          || Double.isNaN(start_.x()) || Double.isNaN(start_.y())
          || Double.isNaN(finish_.x()) || Double.isNaN(finish_.y())) {
      return null;
    }

    if (delta_.y() == 0) {
      return start_.y() > 0 ? sector : null;
    }

    var tX = -start_.y() / delta_.y();

    vec2    newStart_;
    vec2    newFinish_;
    boolean newInfBefore_;
    boolean newInfAfter_;

    IF:
    if (start_.y() > 0) {

      if (tX < 0) {

        if (infiniteBefore_ == false) {
          return sector;
        }

        newStart_     = start_.plus(delta_.mul(tX));
        newFinish_    = finish_;
        newInfBefore_ = false;
        newInfAfter_  = infiniteAfter_;
        break IF;
      }

      if (tX > 1) {

        if (infiniteAfter_ == false) {
          return sector;
        }

        newStart_     = start_;
        newFinish_    = start_.plus(delta_.mul(tX));
        newInfBefore_ = infiniteBefore_;
        newInfAfter_  = false;
        break IF;
      }

      {
        newStart_     = start_;
        newFinish_    = start_.plus(delta_.mul(tX));
        newInfBefore_ = infiniteBefore_;
        newInfAfter_  = false;
        break IF;
      }

    } else {

      if (tX < 0) {

        if (infiniteBefore_ == false) {
          return null;
        }

        newStart_     = start_.plus(delta_.mul(tX - 1));
        newFinish_    = start_.plus(delta_.mul(tX));
        newInfBefore_ = true;
        newInfAfter_  = false;
        break IF;
      }

      if (tX > 1) {

        if (infiniteAfter_ == false) {
          return null;
        }

        newStart_     = start_.plus(delta_.mul(tX));
        newFinish_    = start_.plus(delta_.mul(tX + 1));
        newInfBefore_ = false;
        newInfAfter_  = true;
        break IF;

      }

      {
        newStart_     = start_.plus(delta_.mul(tX));
        newFinish_    = finish_;
        newInfBefore_ = false;
        newInfAfter_  = infiniteAfter_;
        break IF;
      }
    }

    var newStart  = newStart_.unScalar(ox, oy).plus(planeStart);
    var newFinish = newFinish_.unScalar(ox, oy).plus(planeStart);

    return new sector2(newStart, newFinish, newInfBefore_, newInfAfter_);
  }
}
