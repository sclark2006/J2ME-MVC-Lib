package com.fclark.mob.mvc;


import com.fclark.util.List;
import com.fclark.util.UnimplementedMethodException;
import java.util.Enumeration;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

public class ListView extends javax.microedition.lcdui.List implements View {
    protected CRUDController controller;
    
    public ListView() {
        this("Items list", javax.microedition.lcdui.List.IMPLICIT);
    }
    
    public ListView(Enumeration data) {
        this();
        this.write(data); 
    }
    
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
        
    public void write(Object object) {
        if(object != null && object instanceof List)
        {
            clear();
            Enumeration elements = ((List) object).elements();
            while(elements.hasMoreElements()) {
                append(elements.nextElement().toString(), null);
            }
        }
    }

    public Object read(Object object) {
        if(object != null && object instanceof List) {
            if(this.getSelectedIndex() >= 0) {
                return ((List) object).get(this.getSelectedIndex());
            }
            else
                return null;
        }
        else
            return null;
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnimplementedMethodException();
    }
}
