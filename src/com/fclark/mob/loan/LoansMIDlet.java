package com.fclark.mob.loan;  

import com.fclark.mob.loan.controllers.ApplicationController;
import com.fclark.mob.mvc.Controller;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class LoansMIDlet extends MIDlet {


    private Controller appController;

    public LoansMIDlet() {
        
        appController = ApplicationController.getInstance(this);
    }
    

    protected void startApp() throws MIDletStateChangeException {        
        appController.home();
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
        this.notifyPaused();
    }

    /// 

}
