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
import com.fclark.util.Collections;
import com.fclark.util.List;

/**
 *
 * @author Frederick
 * @version 0.1 06/25/2011 08:45:11 PM
 */
public class Loan extends Entity {
    
    public static final Field PERSON_ID = createField(Loan.class, Field.INT),
            APPLICATION_DATE = createField(Loan.class, Field.DATE),
            AMOUNT = createField(Loan.class, Field.DOUBLE),
            STARTS_ON = createField(Loan.class, Field.DATE),
            PAYMENTS = createField(Loan.class, Field.SHORT),
            CURRENT_BALANCE = createField(Loan.class, Field.DOUBLE);

    private Person person;
    private List transactions;
    
    public Loan() {
        setStoreName("Loans");
        person = null;
        transactions = null;
    }
    
    public Loan(Person person) {
        setStoreName("Loans");
        this.setPerson(person);
    }
    
    private void loadTransactions() {
        transactions = Transaction.findBy(Transaction.LOAN_ID, this.get(ID), Transaction.ID, Collections.ORDER_DESCENDING);
    }
    
    public Person getPerson() {
        return person;
    }
   
    public final void setPerson(Person person) {
        this.person = person;
        if(person != null)
            this.setInt(PERSON_ID, this.person.getId());
        else
            this.setInt(PERSON_ID, 0);
    }

    public List getTransactions() {
        if(transactions == null)
            loadTransactions();
        return transactions;
    }

    public void setTransactions(List transactions) {
        this.transactions = transactions;
    }
    
    public void addTransaction(Transaction trx) {
        if(trx != null) {
            trx.setInt(Transaction.LOAN_ID, this.getId());
            this.transactions.add(trx);
        }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc=" Static Query Methods"> 
    // Static Entity Methods Overriding - do not modify                     
    public static int count() {
        return Entity.count(getStoreName(Loan.class));
    }

    public static List findAll() {
        return Entity.findAll(Loan.class);
    }

    public static List findAll(Field orderBy, int sortType) {
        return Entity.findAll(Loan.class, orderBy, sortType);
    }

    public static Loan findById(int id) {
        return (Loan) Entity.findById(Loan.class, id);
    }

    public static Loan findByRecordId(int recordId) {
        return (Loan) Entity.findByRecordId(Loan.class, recordId);
    }

    public static List findBy(Field field, Object value) {
        return Entity.findBy(Loan.class, field, value);
    }

    public static List findBy(Field field, Object value, Field orderBy, int sortType) {
        return Entity.findBy(Loan.class, field, value, orderBy, sortType);
    }

    public static Object min(Field field) {
        return Entity.min(Loan.class, field);
    }

    public static Object max(Field field) {
        return Entity.max(Loan.class, field);
    }

    public static Loan first() {
        return (Loan) Entity.first(Loan.class, ID);
    }

    public static Loan first(Field field) {
        return (Loan) Entity.first(Loan.class, field);
    }

    public static Loan last() {
        return (Loan) Entity.last(Loan.class, ID);
    }

    public static Loan last(Field field) {
        return (Loan) Entity.last(Loan.class, field);
    }
    // End of Static Entity Methods Overriding
    // </editor-fold>    
}
