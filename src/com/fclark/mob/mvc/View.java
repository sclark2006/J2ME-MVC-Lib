package com.fclark.mob.mvc;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public interface View extends CommandListener {
    void setController(Controller ctl);
    void write(Object object);
    Object read(Object object);
    void clear();
    Displayable asDisplayable();
}
