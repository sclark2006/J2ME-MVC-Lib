package com.fclark.mob.loan.views;

import java.util.Enumeration;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import com.fclark.mob.loan.controllers.ApplicationController;
import com.fclark.mob.loan.controllers.LoansController;
import com.fclark.mob.loan.models.Loan;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.loan.models.Transaction;
import com.fclark.mob.mvc.ListView;
import com.fclark.util.Collections;

public final class LoansListView extends ListView {

    private Command exitCommand;

    private Command backCommand;

    private Command newCommand;

    private Command deleteCommand;

    private Command editCommand;

    private Command addTrxCommand;

    private Command listTrxCommand;

    public LoansListView(String title, int listType) {
        super(title, listType);
        initComponents();
    }

    public LoansListView() {
        this("Loans List", List.IMPLICIT);
    }

    public LoansListView(Enumeration data) {
        this();
        this.write(data);
    }

    private void initComponents() {
        this.addCommand(this.backCommand = new Command("Back", Command.BACK, 0));
        this.addCommand(this.newCommand = new Command("New", Command.SCREEN, 0));
        this.addCommand(this.editCommand = new Command("Details", Command.ITEM,
                0));
        this.addCommand(this.deleteCommand = new Command("Delete",
                Command.ITEM, 1));
        this.addCommand(this.addTrxCommand = new Command("Add Trans.",
                Command.ITEM, 2));
        this.addCommand(this.listTrxCommand = new Command("Transactions...",
                Command.ITEM, 2));
        this.addCommand(this.exitCommand = new Command("Exit", Command.EXIT, 10));
        this.setSelectCommand(listTrxCommand);

    }

    public void write(Object item) {
       super.write(item);
       
       if(controller.getCurrentItem() != null) {
           Loan loan =  (Loan)controller.getCurrentItem();
           if(loan.getPerson() != null) {
               this.setTitle(loan.getPerson().getString(Person.NAME) + "'s loan.");
           }
       }      
    }

    public void commandAction(Command command, Displayable display) {
        if (exitCommand.equals(command)) {
            ApplicationController.exit();
        } else if (backCommand.equals(command)) {
            controller.back();
        } else if (newCommand.equals(command)) {
            controller.blank();
        } else if (deleteCommand.equals(command)) {
            controller.delete();
        } else if (editCommand.equals(command)) {
            controller.edit();
        } else if (addTrxCommand.equals(command)) {
            ((LoansController) controller).transactions().blank(
                    (Loan) controller.getCurrentItem());
        } else if (listTrxCommand.equals(command)) {
            ((LoansController) controller).transactions().list(
                    Transaction.LOAN_ID,
                    controller.getCurrentItem().get(Loan.ID),
                    Transaction.TRX_DATE, Collections.ORDER_DESCENDING);
        }
    }
}
