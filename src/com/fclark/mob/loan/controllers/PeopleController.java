package com.fclark.mob.loan.controllers;


import javax.microedition.lcdui.Ticker;

import com.fclark.mob.db.Field;
import com.fclark.mob.loan.Messages;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.views.PersonEdit;
import com.fclark.mob.loan.views.PeopleListView;
import com.fclark.mob.mvc.CRUDController;
import com.fclark.mob.mvc.EditView;
import com.fclark.mob.mvc.ListView;
import com.fclark.util.Collections;
import com.fclark.util.DisplayManager;

public class PeopleController extends CRUDController {

    private LoansController loansController;
    private int siguienteOrden = Collections.ORDER_ASCENDING;
    private ListView peopleListView;
    private EditView personEdit;

    public PeopleController(DisplayManager renderer) {
        super(renderer);
    }
    
    
    private ListView getListView() {
        if(peopleListView == null) {
            peopleListView = new PeopleListView();
            peopleListView.setController(this);
        }
        return peopleListView;
    } 
    
    private EditView getEditorView() {
        if(personEdit == null) { 
            personEdit = new PersonEdit();
            personEdit.setController(this);
        }
        return personEdit;
    }
    
    private void notificarCumpleaños() {
        if (this.peopleListView.getTicker() == null) {
            this.peopleListView.setTicker(new Ticker("Felicidades:"));
        }
//        Persona persona = new Persona();
//        persona.setFechaNacimiento(new Date());
//        Vector cumpleañeros = ClientsManager.findBy("fechaNacimiento", persona);
//        StringBuffer msgBuilder = new StringBuffer();
//        if (cumpleañeros.isEmpty()) {
//            msgBuilder.append(MSG_NO_HAY_CUMPLEAÑOS);
//        } else {
//            msgBuilder.append(MSG_FELIZ_CUMPLEAÑOS);
//            boolean primer = true;
//            Enumeration enumCumple = cumpleañeros.elements();
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

    
    //Returns an instance to a related controller.LoansController
    public LoansController loans() {
        if(loansController == null)
            loansController = new LoansController(this.getDisplayManager());
        return loansController;
    }

    public void create() {
        if(currentItem != null) {
            currentItem = (Person) getEditorView().read(currentItem);
            Integer id = ((Integer)Person.max(Person.ID));            
            currentItem.setId(id == null ? 1 : id.intValue() + 1);
            if(currentItem.create()) {
                Messages.popMessage(Messages.MSG_SUCESSFULLY_CREATED);
                list();
            }
           else
                Messages.popMessage(Messages.MSG_NOT_SAVED);
            
        }        
    }

    public void save() {
        currentItem = (Person) getEditorView().read(currentItem);
        if(currentItem.update()) {
                Messages.popMessage(Messages.MSG_SUCESSFULLY_SAVED);
            }
           else
                Messages.popMessage(Messages.MSG_NOT_SAVED);
    }

    public void blank() {
        currentItem = new Person();
        getEditorView().clear(); 
        render(getEditorView());
    }
    

    public void home() {
        list();
    }

    public void list() {
        items = Person.findAll(Person.ID, Collections.ORDER_ASCENDING);
        render(getListView(), items.elements());
    }
    
    public void list(String search, Field searchField, Field orderBy, int order) {
        // TODO Auto-generated method stub
        
    }
    
    public void show() {
        if(currentItem != null) {
            getEditorView().setTitle(currentItem.getString(Person.NAME));
            render(getEditorView(), currentItem);
        }
    }

    public void delete() {
        if(currentItem != null) {
            getEditorView().setTitle(currentItem.getString(Person.NAME));
            render(getEditorView(), currentItem);
        }
    }

    public void edit() {
        if(currentItem == null)
            currentItem = new Person();
        getEditorView().setTitle(currentItem.getString(Person.NAME));
        render(getEditorView(), currentItem);
    }

}
