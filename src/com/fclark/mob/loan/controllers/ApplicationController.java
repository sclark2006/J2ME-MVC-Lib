package com.fclark.mob.loan.controllers;

import javax.microedition.midlet.MIDlet;

import com.fclark.mob.mvc.Controller;
import com.fclark.util.DisplayManager;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;

public class ApplicationController extends Controller {

    private static MIDlet application;
    private static ApplicationController instance = null;
    private PeopleController personController;
    private Alert alert;
    
    public ApplicationController(DisplayManager renderer) {
        super(renderer);
    }
    
    public static ApplicationController init(MIDlet application) {
        ApplicationController.application = application;
        if(instance == null)
            instance =  new ApplicationController(new DisplayManager(Display.getDisplay(application)));
        return instance;
    }

    private Alert getAlert() {
        if (this.alert == null) {
            return new Alert("Alerta: ");
        } else {
            return this.alert;
        }
    }

    public static MIDlet getMIDlet() {
        return application;
    }

    public void home() {
        if (personController == null) {
            personController = new PeopleController(getDisplayManager());
        }

        personController.list();
    }

    public void displayMessage(String mensaje) {
        this.alert = getAlert();
        this.alert.setString(mensaje);
        this.alert.setTimeout(Alert.FOREVER);
        this.getDisplayManager().show(alert);
    }
    
    public void popMessage(String mensaje) {
        this.alert = getAlert();
        this.alert.setString(mensaje);
        this.alert.setTimeout(2000);
        this.getDisplayManager().show(alert);
    }
    
    public static ApplicationController getInstance() {
        return instance;
    }
}
