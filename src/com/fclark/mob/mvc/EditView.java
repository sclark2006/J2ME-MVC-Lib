package com.fclark.mob.mvc;

import java.util.Date;

import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;


public abstract class EditView extends Form implements View {
    protected CRUDController controller;
    
    public EditView(String title) {
        super(title);
        this.setCommandListener(this);
    }

    public EditView(String title, Item[] items) {
        super(title, items);
        this.setCommandListener(this);
    } 

    public void setController(Controller ctl) {
        controller = (CRUDController) ctl;
    }
   
    public void clear() {
        try {
            for (int i = 0;; i++) {
                if (this.get(i) instanceof TextField) {
                    ((TextField) this.get(i)).setString(null);
                } else if (this.get(i) instanceof DateField) {
                    ((DateField) this.get(i)).setDate(null);
                }
            }//
        } catch (Exception e) {
        }
    }

    protected void setValue(Item item, Object value) {
        if (item instanceof TextField) {
            String valorFinal = null;
            if (value instanceof String)
                valorFinal = (String) value;
            else {
                if (value != null)
                    valorFinal = value.toString();
            }
            ((TextField) item).setString(valorFinal);
        } else if (item instanceof DateField)
            ((DateField) item).setDate((Date) value);
        else if (item instanceof StringItem)
            ((StringItem) item).setText((String) value);
        else if (item instanceof Gauge)
            ((Gauge) item).setValue(((Integer) value).intValue());
    }
    
    protected Object getValue(Item item) {
        Object result = null;
        if (item instanceof TextField) {
            TextField textField = (TextField) item;
            result = textField.getString();
            if(result == null || "".equals(result)) {
                switch(textField.getConstraints()) {
                    case TextField.NUMERIC:
                        result = new Integer(0);
                        break;
                    case TextField.DECIMAL:
                        result = new Double(0);
                        break;
                    default:
                            break;
                }//switch                 
            }//null           
        }
        else if (item instanceof DateField)
            result = ((DateField) item).getDate();
        else if (item instanceof StringItem)
            result = ((StringItem) item).getText();
        else if (item instanceof Gauge)
            result = new Integer(((Gauge) item).getValue());

        return result;
    }

    public Displayable asDisplayable() {
        return this;
    }

}
