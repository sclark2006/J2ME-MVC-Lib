package com.fclark.mob.loan.controllers;


import javax.microedition.lcdui.Ticker;

import com.fclark.mob.db.Field;
import com.fclark.mob.loan.Messages;
import com.fclark.mob.loan.models.Loan;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.views.LoanEdit;
import com.fclark.mob.loan.views.LoansListView;
import com.fclark.mob.loan.views.PersonEdit;
import com.fclark.mob.mvc.CRUDController;
import com.fclark.mob.mvc.EditView;
import com.fclark.mob.mvc.ListView;
import com.fclark.util.Collections;
import com.fclark.util.DisplayManager;

public class LoansController extends CRUDController {

    private int nextOrder = Collections.ORDER_ASCENDING;
    private ListView loansListView;
    private EditView loanEdit;

    public LoansController(DisplayManager renderer) {
        super(renderer);
    }
        
    private ListView getListView() {
        if(loansListView == null) {
            loansListView = new LoansListView();
            loansListView.setController(this);
        }
        return loansListView;
    } 
    
    private EditView getEditorView() {
        if(loanEdit == null) {
            loanEdit = new LoanEdit();
            loanEdit.setController(this);
        }
        return loanEdit;
    }
        
    public void create() {
        if(currentItem != null) {
            currentItem = (Loan) getEditorView().read(currentItem);
            Integer id = ((Integer)Loan.max(Loan.ID));
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
        currentItem = new Loan();
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

    public void list(Object search, Field searchField, Field orderBy, int order) {
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
