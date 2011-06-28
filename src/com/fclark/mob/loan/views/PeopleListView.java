package com.fclark.mob.loan.views;

import java.util.Enumeration;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import com.fclark.mob.loan.controllers.ApplicationController;
import com.fclark.mob.mvc.ListView;

public final class PeopleListView extends ListView {

    private Command exitCommand;
    private Command newItemCommand;
    private Command deleteCommand;
    private Command sortCommand;
    private Command showBirthdaysCommand;
    private boolean isShowingBirthdays = false;
    
    public PeopleListView(String title, int listType) {
        super(title, listType);
        initComponents();
    }
    
    public PeopleListView() {
        this("Person List", List.IMPLICIT);
    }
    
    public PeopleListView(Enumeration data) {
        this();
        this.write(data); 
    }

    private void initComponents() {
    
        this.exitCommand = new Command("Exit", Command.EXIT, 0);
        this.sortCommand = new Command("Sort (Age)", Command.SCREEN, 1);
        this.newItemCommand = new Command("New", Command.SCREEN, 2);
        this.deleteCommand = new Command("Delete", Command.ITEM, 3);
        this.showBirthdaysCommand = new Command("Birthdays", Command.SCREEN, 3);

        this.addCommand(this.exitCommand);
        this.addCommand(this.newItemCommand);
        this.addCommand(this.deleteCommand);
        this.addCommand(this.sortCommand);
        this.addCommand(this.showBirthdaysCommand);
        this.setSelectCommand(List.SELECT_COMMAND);
    }
    
    public void write(Object item) {
        if(item != null && item instanceof Enumeration)
        {
            clear();
            Enumeration elements = (Enumeration)item;
            while(elements.hasMoreElements()) {
                append(elements.nextElement().toString(), null);
            }
        }
    }

    public Object read(Object item) {
        return new Integer(this.getSelectedIndex());
    }
    
    public void commandAction(Command command, Displayable display) {
        if(exitCommand.equals(command))  ApplicationController.getMIDlet().notifyDestroyed();
        if(newItemCommand.equals(command)) controller.blank();
        if(deleteCommand.equals(command)) controller.delete();
        if(sortCommand.equals(command)) controller.list();
        if(List.SELECT_COMMAND.equals(command)) controller.show();
        //if(showBirthdaysCommand.equals(command))   
    }

}
