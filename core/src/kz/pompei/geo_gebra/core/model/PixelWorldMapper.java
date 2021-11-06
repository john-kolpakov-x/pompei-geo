package kz.pompei.geo_gebra.core.model;

public class PixelWorldMapper {
  public double xCenter = 0, yCenter = 0, scaleX = 1, scaleY = 1;

  public void assign(PixelWorldMapper a) {
    xCenter = a.xCenter;
    yCenter = a.yCenter;
    scaleX  = a.scaleX;
    scaleY  = a.scaleY;
  }

  public PixelWorldMapper() {
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

  public static PixelWorldMapper deserialize(String str) {
    if (str == null) {
      return new PixelWorldMapper();
    }

    var split = str.trim().split("\\s+");
    if (split.length != 4) return new PixelWorldMapper();

    try {
      var rete = new PixelWorldMapper();
      rete.xCenter = Double.parseDouble(split[0]);
      rete.yCenter = Double.parseDouble(split[1]);
      rete.scaleX  = Double.parseDouble(split[2]);
      rete.scaleY  = Double.parseDouble(split[3]);
      return rete;
    } catch (NumberFormatException ignore) {
      return new PixelWorldMapper();
    }

  }
}
