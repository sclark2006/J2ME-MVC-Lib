/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.loan.views;

import com.fclark.mob.loan.controllers.LoansController;
import com.fclark.mob.loan.controllers.PeopleController;
import com.fclark.mob.loan.models.Loan;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.models.Transaction;
import com.fclark.mob.mvc.EditView;
import com.fclark.util.Collections;

import javax.microedition.lcdui.*;

/**
 * 
 * @author Frederick
 * @version 06/25/2011 07:11:12 PM
 */
public class LoanEdit extends EditView implements ItemStateListener {
    private Command backCommand;

    private Command saveCommand;

    private Command newItemCommand;

    private Command deleteCommand;

    private Command transxCommand;

    private boolean editing;

    public LoanEdit() {
        this("New Loan");
        editing = false;
    }

    public LoanEdit(String title) {
        super(title);
        initComponents();
        editing = false;
    }

    private void initComponents() {
        // viewTrxCommand
        this.addCommand(this.backCommand = new Command("Back", Command.BACK, 0));
        this.addCommand(this.newItemCommand = new Command("New",
                Command.SCREEN, 1));
        this.addCommand(this.saveCommand = new Command("Save", Command.SCREEN,
                2));
        this.addCommand(this.deleteCommand = new Command("Delete",
                Command.SCREEN, 3));
        this.addCommand(this.transxCommand = new Command("Transactions...",
                Command.SCREEN, 4));

        this.append(new StringItem("Person:", null, StringItem.BUTTON));
        this.append(new DateField("Apl.Date:", DateField.DATE));
        this.append(new TextField("Amount:", null, 15, TextField.DECIMAL));
        this.append(new DateField("Start on::", DateField.DATE));
        this.append(new TextField("Payments #:", "1", 3, TextField.NUMERIC));
        this.append(new TextField("Balance:", "0.0", 15, TextField.DECIMAL));
        this.setItemStateListener(this);
    }

    public void write(Object item) {
        if (item != null) {
            Loan loan = (Loan) item;
            editing = loan.getRecordId() > 0 ? true : false;
            Person person = loan.getPerson();
            if (person != null) {
                setValue(get(0), person.get(Person.NAME));
                this.setTitle(person.getString(Person.NAME) + "'s loan. ID:"
                        + loan.getId());
            }
            setValue(get(1), loan.getDate(Loan.APPLICATION_DATE));
            setValue(get(2), loan.get(Loan.AMOUNT));
            setValue(get(3), loan.getDate(Loan.STARTS_ON));
            setValue(get(4), loan.get(Loan.PAYMENTS));
            setValue(get(5), loan.get(Loan.CURRENT_BALANCE));
        }
    }

    // public
    public Object read(Object item) {
        Loan loan = null;
        if (item != null && item instanceof Loan) {
            loan = (Loan) item;
            editing = loan.getRecordId() > 0 ? true : false;
            loan.set(Loan.APPLICATION_DATE, getValue(get(1)));
            loan.set(Loan.AMOUNT, getValue(get(2)));
            loan.set(Loan.STARTS_ON, getValue(get(3)));
            loan.set(Loan.PAYMENTS, getValue(get(4)));
            loan.set(Loan.CURRENT_BALANCE, getValue(get(5)));
            // Updates the balance of the person to the new balance
            if (loan.getPerson() != null)
                loan.getPerson().set(Person.BALANCE,
                        loan.get(Loan.CURRENT_BALANCE));
        }
        return loan;
    }

    public void commandAction(Command command, Displayable display) {
        if (command.getCommandType() == Command.BACK)
            controller.back();
        if (saveCommand.equals(command))
            if (editing)
                controller.save();
            else
                controller.create();
        if (newItemCommand.equals(command))
            controller.blank();
        if (deleteCommand.equals(command))
            controller.delete();
        // Load all the transactions attached to this loan
        if (transxCommand.equals(command)) {
            ((LoansController) controller).transactions().list(
                    Transaction.LOAN_ID,
                    controller.getCurrentItem().get(Loan.ID),
                    Transaction.TRX_DATE, Collections.ORDER_DESCENDING);
        }

    }

    public void itemStateChanged(Item item) {
        if (item.getLabel().startsWith("Amount") && !editing) {
            ((TextField) get(5)).setString(((TextField) item).getString());
        }// if
    }// itemStateChanged

}