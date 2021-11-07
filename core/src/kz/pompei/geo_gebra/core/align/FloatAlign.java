package kz.pompei.geo_gebra.core.align;

public class FloatAlign {
  private int leftSize = 0, rightSize = 0;

  public FloatAlign append(int value) {
    appendStr("" + value);
    return this;
  }

  public FloatAlign append(float value) {
    appendStr("" + value);
    return this;
  }

  public FloatAlign append(double value) {
    appendStr("" + value);
    return this;
  }

  private void appendStr(String str) {
    leftSize  = Math.max(leftSize, calcLeftSize(str));
    rightSize = Math.max(rightSize, calcRightSize(str));
  }

  private int calcLeftSize(String str) {
    return calcIndex(str);
  }

  private int calcRightSize(String str) {
    return str.length() - calcIndex(str);
  }

  private int calcIndex(String str) {
    {
      var idx = str.indexOf('.');
      if (idx >= 0) return idx;
    }
    {
      var idx = str.indexOf(',');
      if (idx >= 0) return idx;
    }
    return str.length();
  }

  public String display(int value) {
    return displayStr("" + value);
  }

  public String display(float value) {
    return displayStr("" + value);
  }

  public String display(double value) {
    return displayStr("" + value);
  }

  private String displayStr(String str) {
    var rete        = new StringBuilder();
    for (int i = 0, c = leftSize - calcLeftSize(str); i < c; i++) {
      rete.append(' ');
    }
    rete.append(str);
    for (int i = 0, c = rightSize - calcRightSize(str); i < c; i++) {
      rete.append(' ');
    }
    return rete.toString();
  }
}
