/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * MIDP Entity Template 0.2 by Frederick Clark
 * Jun 25th, 2011.
 */
package com.fclark.mob.loan.models;

import com.fclark.mob.db.Field;
import com.fclark.mob.db.Entity;
import com.fclark.util.List;

/**
 *
 * @author Frederick
 * @version 0.1 06/26/2011 12:49:36 AM
 */
public class Transaction extends Entity {

    public static final Field LOAN_ID = createField(Transaction.class, Field.INT),
            TRX_DATE = createField(Transaction.class, Field.DATE),
            TYPE = createField(Transaction.class, Field.CHAR),
            AMOUNT = createField(Transaction.class, Field.DOUBLE);
    private Loan loan;

    public Transaction() {
        setStoreName("Transactions");
        loan = null;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        if(loan != null)
            this.setInt(LOAN_ID, this.loan.getId());
        else
            this.setInt(LOAN_ID, 0);
    }

    // <editor-fold defaultstate="collapsed" desc=" Static Query Methods"> 
    // Static Entity Methods Overriding - do not modify                     
    public static int count() {
        return Entity.count(getStoreName(Transaction.class));
    }

    public static List findAll() {
        return Entity.findAll(Transaction.class);
    }

    public static List findAll(Field orderBy, int sortType) {
        return Entity.findAll(Transaction.class, orderBy, sortType);
    }

    public static Transaction findById(int id) {
        return (Transaction) Entity.findById(Transaction.class, id);
    }

    public static Transaction findByRecordId(int recordId) {
        return (Transaction) Entity.findByRecordId(Transaction.class, recordId);
    }

    public static List findBy(Field field, Object value) {
        return Entity.findBy(Transaction.class, field, value);
    }

    public static List findBy(Field field, Object value, Field orderBy, int sortType) {
        return Entity.findBy(Transaction.class, field, value, orderBy, sortType);
    }

    public static Object min(Field field) {
        return Entity.min(Transaction.class, field);
    }

    public static Object max(Field field) {
        return Entity.max(Transaction.class, field);
    }

    public static Transaction first() {
        return (Transaction) Entity.first(Transaction.class, ID);
    }

    public static Transaction first(Field field) {
        return (Transaction) Entity.first(Transaction.class, field);
    }

    public static Transaction last() {
        return (Transaction) Entity.last(Transaction.class, ID);
    }

    public static Transaction last(Field field) {
        return (Transaction) Entity.last(Transaction.class, field);
    }
    // End of Static Entity Methods Overriding
    // </editor-fold>    
}
