package com.fclark.mob.loan.views;

import java.util.Enumeration;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import com.fclark.mob.loan.controllers.ApplicationController;
import com.fclark.mob.loan.controllers.PeopleController;
import com.fclark.mob.mvc.ListView;

public final class PeopleListView extends ListView {

    private Command exitCommand;
    private Command newItemCommand;
    private Command deleteCommand;
    private Command editCommand;
    private Command sortCommand;
    private Command showBirthdaysCommand;
    private boolean isShowingBirthdays = false;
    
    public PeopleListView(String title, int listType) {
        super(title, listType);
        initComponents();
    }
    
    public PeopleListView() {
        this("People List", List.IMPLICIT);
    }
    
    public PeopleListView(Enumeration data) {
        this();
        this.write(data); 
    }

    private void initComponents() {
        this.addCommand(this.exitCommand = new Command("Exit", Command.EXIT, 0));
        this.addCommand(this.newItemCommand = new Command("New", Command.SCREEN, 0));
        this.addCommand(this.editCommand  = new Command("Edit", Command.ITEM, 0));
        this.addCommand(this.deleteCommand = new Command("Delete", Command.ITEM, 1));        
        this.addCommand(this.sortCommand  = new Command("Sort (Age)", Command.SCREEN, 1));
        this.addCommand(this.showBirthdaysCommand = new Command("Birthdays", Command.SCREEN, 2));
        this.setSelectCommand(editCommand);
    }

    
    public void commandAction(Command command, Displayable display) {
        if(exitCommand.equals(command))  ApplicationController.exit();
        if(newItemCommand.equals(command)) controller.blank();
        if(editCommand.equals(command)) controller.edit();
        if(deleteCommand.equals(command)) controller.delete();
        if(sortCommand.equals(command)) ((PeopleController)controller).toggleSort();
        //if(showBirthdaysCommand.equals(command))   
    }

}
