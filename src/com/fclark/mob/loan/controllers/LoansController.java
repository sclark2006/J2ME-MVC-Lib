package com.fclark.mob.loan.controllers;

import com.fclark.mob.db.Entity;
import com.fclark.mob.loan.models.Loan;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.views.LoanEdit;
import com.fclark.mob.loan.views.LoansListView;
import com.fclark.mob.mvc.EditView;
import com.fclark.mob.mvc.ListView;
import com.fclark.mob.mvc.ShowView;

public class LoansController extends com.fclark.mob.mvc.CRUDController {
    private TransactionsController trxController;

    private ListView loansListView;

    private EditView loanEdit;

    public LoansController(com.fclark.util.DisplayManager renderer) {
        super(renderer, Loan.class);
    }

    public ListView getListView() {
        if (loansListView == null) {
            loansListView = new LoansListView();
            loansListView.setController(this);
        }
        return loansListView;
    }

    public EditView getEditView() {
        if (loanEdit == null) {
            loanEdit = new LoanEdit();
            loanEdit.setController(this);
        }
        return loanEdit;
    }
    
    public void blank(Person owner) {

        //if(currentItem != null && !currentItem.exists())
        //    if(Messages.confirm("Tiene cambios pendientes por grabar ¿Desea guardarlos?"))
        //        currentItem.save();
        currentItem = new Loan(owner);
        getEditView().clear();
        render(getEditView(), currentItem);
    }
    
    
    // Returns an instance to a related controller LoansController
    public TransactionsController transactions() {
        if (trxController == null)
            trxController = new TransactionsController(this.getDisplayManager());
        return trxController;
    }
}
