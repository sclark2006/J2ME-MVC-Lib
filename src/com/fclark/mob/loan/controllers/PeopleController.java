package com.fclark.mob.loan.controllers;


import javax.microedition.lcdui.Ticker;

import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.views.PersonEdit;
import com.fclark.mob.loan.views.PeopleListView;
import com.fclark.mob.mvc.EditView;
import com.fclark.mob.mvc.ListView;
import com.fclark.util.Collections;
import com.fclark.util.DisplayManager;

public class PeopleController extends com.fclark.mob.mvc.CRUDController {

    private LoansController loansController;
    private int nextOrder;

    public PeopleController(DisplayManager renderer) {
        super(renderer, Person.class);
        nextOrder = Collections.ORDER_DESCENDING;
    }
    
    
    public ListView getListView() {
        if(listView == null) {
            listView = new PeopleListView();
            listView.setController(this);
        }
        return listView;
    } 
    
    public EditView getEditView() {
        if(editView == null) { 
            editView = new PersonEdit();
            editView.setController(this);
        }
        return editView;
    }
    
    private void notificarCumplea�os() {
        if (this.listView.getTicker() == null) {
            this.listView.setTicker(new Ticker("Felicidades:"));
        }
            
//        Persona persona = new Persona();
//        persona.setFechaNacimiento(new Date());
//        Vector cumplea�eros = ClientsManager.findBy("fechaNacimiento", persona);
//        StringBuffer msgBuilder = new StringBuffer();
//        if (cumplea�eros.isEmpty()) {
//            msgBuilder.append(MSG_NO_HAY_CUMPLEA�OS);
//        } else {
//            msgBuilder.append(MSG_FELIZ_CUMPLEA�OS);
//            boolean primer = true;
//            Enumeration enumCumple = cumplea�eros.elements();
//            while (enumCumple.hasMoreElements()) {
//                if (!primer) {
//                    msgBuilder.append(", ");
//                }
//                msgBuilder.append(((Persona) enumCumple.nextElement()).getNombre());
//                primer = false;
//            }
//        }
//        isShowingBirthdays = true;
        //this.personList.getTicker().setString(msgBuilder.toString());
    }
    
    public void toggleSort() {
        list(nextOrder);
        
        if(nextOrder == Collections.ORDER_ASCENDING)
            nextOrder = Collections.ORDER_DESCENDING;
        else
            nextOrder = Collections.ORDER_ASCENDING;
    }
    
    //Returns an instance to a related controller LoansController
    public LoansController loans() {
        if(loansController == null)
            loansController = new LoansController(this.getDisplayManager());
        return loansController;
    }
}
