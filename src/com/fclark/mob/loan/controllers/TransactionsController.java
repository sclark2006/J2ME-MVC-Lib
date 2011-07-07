package com.fclark.mob.loan.controllers;

import com.fclark.mob.loan.models.Transaction;
import com.fclark.mob.loan.views.TransactionEdit; 
import com.fclark.mob.loan.views.TransactionsListView;
import com.fclark.mob.mvc.CRUDController;
import com.fclark.mob.mvc.EditView;
import com.fclark.mob.mvc.ListView;

public class TransactionsController extends CRUDController {

    private ListView trxListView;
    private EditView trxEdit;

    public TransactionsController(com.fclark.util.DisplayManager renderer) {
        super(renderer, Transaction.class);
    }
    
    public ListView getListView() {
        if(trxListView == null) {
            trxListView = new TransactionsListView();
            trxListView.setController(this);
        }
        return trxListView;
    } 
    
    public EditView getEditView() {
        if(trxEdit == null) {
            trxEdit = new TransactionEdit();
            trxEdit.setController(this);
        }
        return trxEdit;
    }

}
