package com.fclark.mob.loan.views;


import com.fclark.mob.loan.controllers.PeopleController;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

import com.fclark.mob.loan.models.Loan;
import com.fclark.mob.loan.models.Person;
import com.fclark.mob.mvc.EditView;
import com.fclark.util.Collections;
import com.fclark.util.TimeSpan;
import java.util.Date;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;

public class PersonEdit extends EditView implements ItemStateListener {

    private Command backCommand;
    private Command saveCommand;
    private Command newItemCommand;
    private Command deleteCommand;
    private Command loansCommand;
    
    private boolean editing;
    

    public PersonEdit() {
        this("New Person");
    }
    public PersonEdit(String title) {
        super(title);
        initComponents();
        editing = false;
    } 
    
    private void initComponents() {
        this.backCommand = new Command("Back", Command.BACK, 0);
        this.saveCommand = new Command("Save", Command.SCREEN, 1);
        this.newItemCommand = new Command("New", Command.SCREEN, 2);
        this.deleteCommand = new Command("Delete", Command.SCREEN, 3);
        this.loansCommand = new Command("Loans", Command.SCREEN, 4);
        
        this.addCommand(this.backCommand);
        this.addCommand(this.saveCommand);
        this.addCommand(this.newItemCommand);
        this.addCommand(this.deleteCommand);
        this.addCommand(this.loansCommand);
        
        this.append(new TextField("Name: *", null, 30, TextField.ANY + TextField.INITIAL_CAPS_WORD));
        this.append(new TextField("Address:", null, 50, TextField.ANY));
        this.append(new TextField("Phone:", null, 15, TextField.PHONENUMBER));
        this.append(new TextField("e-mail:", null, 30, TextField.EMAILADDR));
        this.append(new DateField("Birthdate: *", DateField.DATE));
        this.append(new StringItem("Age:", null));
        this.append(new StringItem("Balance:", null));
        this.setItemStateListener(this);
    }

    public void write(Object item) {
        if(item != null) {
            Person person = (Person)item;
            editing = person.getRecordId() > 0 ? true : false;
            setValue(get(0), person.getString(Person.NAME));
            setValue(get(1), person.getString(Person.ADDRESS));
            setValue(get(2), person.getString(Person.PHONE));
            setValue(get(3), person.getString(Person.EMAIL));
            setValue(get(4), person.getDate(Person.BIRTHDATE));
            setValue(get(5), new Integer(person.age()));
            setValue(get(6), person.get(Person.BALANCE));
            if(editing)
                this.setTitle(person.getString(Person.NAME) + " detail");
        }
    }
    
    //public 
    public Object read(Object item) {
        // TODO Auto-generated method stub
        Person person = null;
        if(item != null && item instanceof Person) {
            person = (Person)item;
            editing = person.getRecordId() > 0 ? true : false;
            person.set(Person.NAME, getValue(get(0)));
            person.set(Person.ADDRESS, getValue(get(1)));
            person.set(Person.PHONE, getValue(get(2)));
            person.set(Person.EMAIL, getValue(get(3)));
            person.set(Person.BIRTHDATE, getValue(get(4)));
        }
        return person;
    }
    
    public void commandAction(Command command, Displayable display) {
        if(command.getCommandType() == Command.BACK) controller.back();
        if(saveCommand.equals(command)) 
            if(editing) 
                controller.save();
            else
                controller.create();
        if(newItemCommand.equals(command)) controller.blank();
        if(deleteCommand.equals(command)) controller.delete();
        //Load all the loans attached to this person
        if(loansCommand.equals(command))
            ((PeopleController)controller).loans().list(Loan.PERSON_ID, controller.getCurrentItem().get(Person.ID), 
                    Loan.ID, Collections.ORDER_ASCENDING);
    }
    
    public void itemStateChanged(Item item) {
        //If is the BirthDate field
        if(item.equals(get(4))) {
            TimeSpan age = TimeSpan.between(new Date(), ((DateField)item).getDate());
            if(age != null)
                setValue(get(5), new Integer(age.getYears()));                
            
        }
    }

}
