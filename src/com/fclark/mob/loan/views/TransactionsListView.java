package com.fclark.mob.loan.views;

import java.util.Enumeration;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import com.fclark.mob.loan.controllers.ApplicationController;
import com.fclark.mob.mvc.ListView;

public final class TransactionsListView extends ListView {

    private Command backCommand;
    private Command newCommand;
    private Command deleteCommand;
    private Command showCommand;

    public TransactionsListView() {
        this("Transactions list", List.IMPLICIT);
    }

    public TransactionsListView(Enumeration data) {
        this();
        this.write(data);
    }

    public TransactionsListView(String title, int listType) {
        super(title, listType);
        initComponents();
    }


    private void initComponents() {

        this.backCommand = new Command("Back", Command.BACK, 0);
        this.newCommand = new Command("New", Command.SCREEN, 0);
        this.showCommand = new Command("Show", Command.ITEM, 0);
        this.deleteCommand = new Command("Delete", Command.ITEM, 1);


        this.addCommand(this.backCommand);
        this.addCommand(this.newCommand);
        this.addCommand(this.showCommand);
        this.addCommand(this.deleteCommand);
        this.setSelectCommand(this.showCommand);
    }

    public void write(Object item) {
        if (item != null && item instanceof Enumeration) {
            clear();
            Enumeration elements = (Enumeration) item;
            while (elements.hasMoreElements()) {
                append(elements.nextElement().toString(), null);
            }
        }
    }

    public Object read(Object item) {
        return new Integer(this.getSelectedIndex());
    }

    public void commandAction(Command command, Displayable display) {
        if (backCommand.equals(command)) {
            ApplicationController.exit();
        }
        if (newCommand.equals(command)) {
            controller.blank();
        }
        if (deleteCommand.equals(command)) {
            controller.delete();
        }
        if (showCommand.equals(command)) {
            controller.show();
        }

    }
}
