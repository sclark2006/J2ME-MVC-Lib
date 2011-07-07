package com.fclark.mob.loan.controllers;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import com.fclark.mob.mvc.Controller;
import com.fclark.util.DisplayManager;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;

public class ApplicationController extends Controller {

    private static MIDlet application;
    private static ApplicationController instance = null;
    private PeopleController personController;
    private Alert alert;
    private Alert confirmation;

    public ApplicationController(DisplayManager renderer) {
        super(renderer);
    }

    
    private Alert getAlert() {
        if (this.alert == null)
            alert = new Alert("Alert");
        return this.alert;        
    }
    
    private Alert getConfirmation() {
        if (this.confirmation == null)  {
            confirmation  = new Alert("Confirmation needed");
            confirmation.setType(AlertType.WARNING);
            confirmation.setTimeout(Alert.FOREVER);
            confirmation.addCommand(new Command("Yes", Command.OK, 0));
            confirmation.addCommand(new Command("No", Command.CANCEL, 1));
        }
        return this.confirmation;        
    }
    
    public static void exit() {
        application.notifyDestroyed();
    }

    public void home() {
        if (personController == null) {
            personController = new PeopleController(getDisplayManager());
        }
        personController.list();
    }
    
    public void confirm(String message, final com.fclark.util.ConfirmationCallBack callBack) {
        getConfirmation().setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
                if(c.getCommandType() == Command.OK)
                    callBack.confirm(true);
                else
                    callBack.confirm(false);
            }
        });
        getConfirmation().setString(message);
        getDisplayManager().show(getConfirmation());
    }

    public void displayMessage(String message) {        
        getAlert().setString(message);
        getAlert().setTimeout(Alert.FOREVER);
        getDisplayManager().show(getAlert());
    }
    
    public void popMessage(String message) {
        getAlert().setString(message);
        getAlert().setTimeout(3000);
        getDisplayManager().show(getAlert());
    }
    
    public static ApplicationController getInstance(MIDlet midlet) {
        application = midlet;
        return getInstance();
    }
    
    public static ApplicationController getInstance() {
        if(instance == null && application != null)
            instance =  new ApplicationController(new DisplayManager(Display.getDisplay(application)));       
        
        return instance;
    }
    
    public static MIDlet getApp() { 
        return application;
    }
}
