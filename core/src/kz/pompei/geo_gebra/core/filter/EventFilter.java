package kz.pompei.geo_gebra.core.filter;

import java.awt.event.MouseEvent;

public class EventFilter {

  public static Mouse mousePressed() {
    return new Mouse(MouseEvent.MOUSE_PRESSED);
  }

  public static Mouse mouseReleased() {
    return new Mouse(MouseEvent.MOUSE_RELEASED);
  }

  public static Mouse mouseDragged() {
    return new Mouse(MouseEvent.MOUSE_DRAGGED);
  }

  private enum MouseButton {
    LEFT, MIDDLE, RIGHT
  }

  private enum KeyState {
    Pressed {
      @Override
      boolean correct(boolean pressed) {
        return pressed == true;
      }
    }, Released {
      @Override
      boolean correct(boolean pressed) {
        return pressed == false;
      }
    }, Any {
      @Override
      boolean correct(boolean pressed) {
        return true;
      }
    };

    abstract boolean correct(boolean pressed);
  }

  @SuppressWarnings("unused")
  public static class Mouse {
    private final int eventType;

    private MouseButton button = MouseButton.LEFT;

    private KeyState ctrl  = KeyState.Released;
    private KeyState shift = KeyState.Released;
    private KeyState alt   = KeyState.Released;
    private KeyState win   = KeyState.Released;

    private int clickCount = 1;

    public Mouse dblClick() {
      clickCount = 2;
      return this;
    }

    public Mouse(int eventType) {
      this.eventType = eventType;
    }

    //<editor-fold desc="ctrl shift alt win">
    public Mouse ctrlReleased() {
      ctrl = KeyState.Released;
      return this;
    }

    public Mouse ctrlPressed() {
      ctrl = KeyState.Pressed;
      return this;
    }

    public Mouse ctrlAny() {
      ctrl = KeyState.Any;
      return this;
    }

    public Mouse shiftReleased() {
      shift = KeyState.Released;
      return this;
    }

    public Mouse shiftPressed() {
      shift = KeyState.Pressed;
      return this;
    }

    public Mouse shiftAny() {
      shift = KeyState.Any;
      return this;
    }

    public Mouse altReleased() {
      alt = KeyState.Released;
      return this;
    }

    public Mouse altPressed() {
      alt = KeyState.Pressed;
      return this;
    }

    public Mouse altAny() {
      alt = KeyState.Any;
      return this;
    }

    public Mouse winReleased() {
      win = KeyState.Released;
      return this;
    }

    public Mouse winPressed() {
      win = KeyState.Pressed;
      return this;
    }

    public Mouse winAny() {
      win = KeyState.Any;
      return this;
    }
    //</editor-fold>

    public Mouse any() {
      button = null;
      return this;
    }

    public Mouse left() {
      button = MouseButton.LEFT;
      return this;
    }

    public Mouse middle() {
      button = MouseButton.MIDDLE;
      return this;
    }

    public Mouse right() {
      button = MouseButton.RIGHT;
      return this;
    }


    private boolean rightButton(MouseEvent event) {
      if (button == null) return true;
      return event.getButton() == switch (button) {
        case LEFT -> MouseEvent.BUTTON1;
        case MIDDLE -> MouseEvent.BUTTON2;
        case RIGHT -> MouseEvent.BUTTON3;
      };
    }

    private boolean correctClickCount(int clickCount, int eventType) {
      return eventType != MouseEvent.MOUSE_PRESSED || this.clickCount == clickCount;
    }

    public boolean does(MouseEvent event) {

      return event.getID() == eventType
               && rightButton(event)
               && ctrl.correct(event.isControlDown())
               && shift.correct(event.isShiftDown())
               && alt.correct(event.isAltDown())
               && win.correct(event.isMetaDown())
               && correctClickCount(event.getClickCount(), eventType);
    }
  }


}
