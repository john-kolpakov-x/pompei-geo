package kz.pompei.geo_gebra.core.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Painter extends JPanel {
  private final Drawer drawer;

  public Painter(Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    drawer.draw( (Graphics2D)g, getWidth(), getHeight() );
  }
}
