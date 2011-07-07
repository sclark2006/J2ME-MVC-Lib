package com.fclark.mob.mvc;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;


public  class ShowView extends Form implements View {
    protected CRUDController controller;
    
    public ShowView(String title) {
        super(title);
        this.setCommandListener(this);
    }

    public ShowView(String title, Item[] items) {
        super(title, items);
        this.setCommandListener(this);
    }

    public void setController(Controller ctl) {
        controller = (CRUDController) ctl;
    }
   
    public void clear() {
        for (int i = 0;; i++) {
            if (this.get(i) instanceof StringItem) {
                ((StringItem) this.get(i)).setText(null);
            } 
        }//
    }

    protected void setValue(Item item, Object value) {
        if (item instanceof StringItem)
            ((StringItem) item).setText((String) value);        
    }

    public Displayable asDisplayable() {
        return this;
    }

    public void commandAction(Command arg0, Displayable arg1) {
        // TODO Auto-generated method stub
        
    }

    public void write(Object item) {
        // TODO Auto-generated method stub
        
    }

    public Object read(Object item) {
        // TODO Auto-generated method stub
        return null;
    }

}
