package com.fclark.mob.mvc;


import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

public abstract class ListView extends List implements View {
    protected CRUDController controller;
    
    public ListView(String title, int listType) {
        super(title, listType);
        this.setCommandListener(this);        
    }
       
    public void setController(Controller ctl) {
        controller = (CRUDController)ctl;
    }

    public void clear() {
        this.deleteAll();
    }

    public Displayable asDisplayable() {
        return this;
    }

}
