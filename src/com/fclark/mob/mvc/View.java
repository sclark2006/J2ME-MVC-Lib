package com.fclark.mob.mvc;

public interface View extends javax.microedition.lcdui.CommandListener {
    void setController(Controller ctl);
    void write(Object item);
    Object read(Object item);
    void clear();
    javax.microedition.lcdui.Displayable asDisplayable();
}
