package kz.pompei.geo_gebra.core.launch;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import kz.pompei.geo_gebra.core.paint.ScreenRealMapperMediator;
import kz.pompei.geo_gebra.core.paint.PaintPanel;
import kz.pompei.geo_gebra.core.store.StrAcceptorInFile;

public class Launcher {

  public static void main(String[] args) {
    new Launcher().exec();
  }

  private void exec() {
    JFrame frame = new JFrame();
    frame.setTitle("Pompei Geo-Geb Ra");

    frame.setLocation(4000, 100);
    frame.setSize(1200, 600);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    var dataDir = Paths.get("build").resolve(getClass().getSimpleName());

    var pixelWorldMapperSaver = new StrAcceptorInFile(dataDir.resolve("pixelWorldMapper.txt"));

    PaintPanel paintPanel = new PaintPanel(new ScreenRealMapperMediator(pixelWorldMapperSaver));

    frame.setContentPane(paintPanel);

    paintPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        paintPanel.mouseEvent(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        paintPanel.mouseEvent(e);
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        paintPanel.mouseClicked(e);
      }
    });
    paintPanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        paintPanel.mouseEvent(e);
      }
    });
    paintPanel.addMouseWheelListener(new MouseAdapter() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        paintPanel.mouseWheelEvent(e);
      }
    });

    frame.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
          paintPanel.resetMapper();
          return;
        }
      }
    });

    frame.setVisible(true);
  }

}
