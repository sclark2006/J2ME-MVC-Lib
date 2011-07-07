package com.fclark.mob.loan;

import com.fclark.mob.loan.controllers.ApplicationController;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class Messages {
    public static final String MSG_HAPPY_BIRTHDAY = "!Feliz cumpleaños para ";
    public static final String MSG_CONFIRM_DELETION = "¿Está seguro de querer eliminar el registro seleccionado?";
    public static final String MSG_NO_RECORD_SELECTED = "Debe tener un registro seleccionado!";
    public static final String MSG_NO_BIRTHDATES_TODAY = "No hay cumpleaños para el día de hoy!";
    public static final String MSG_RECORD_DELETED = "El registro ha sido eliminado!";
    public static final String MSG_RECORD_NO_DELETED = "El registro no pudo ser eliminado!";
    public static final String MSG_SPECIFY_NAME = "Debe especificar el nombre de la persona";
    public static final String MSG_SPECIFY_AGE = "Debe especificar la edad de la persona";
    public static final String MSG_IS_ADULT = "La persona debe tener 18 años o más";
    public static final String MSG_SUCESSFULLY_CREATED = "Registro creado satisfactoriamente!";
    public static final String MSG_SUCESSFULLY_SAVED = "Cambios guardados satisfactoriamente!";
    public static final String MSG_NOT_SAVED = "El registro no pudo ser guardado!";
    
    
    //private static Alert conf;
    private static Alert alert;
    public static void confirm(String msg, final com.fclark.util.ConfirmationCallBack callBack) {
//        if(conf == null) {
//            conf  = new Alert("Confirmation needed");
//            conf.setType(AlertType.WARNING);
//            conf.setTimeout(Alert.FOREVER);
//            conf.addCommand(new Command("Yes", Command.OK, 0));
//            conf.addCommand(new Command("No", Command.CANCEL, 1));
//        }            
//            conf.setString(msg);                        
//            conf.setCommandListener(new CommandListener() {
//            public void commandAction(Command c, Displayable d) {
//                if(c.getCommandType() == Command.OK)
//                    callBack.confirm(true);
//                else
//                    callBack.confirm(false);
//            }
//        });
//            ApplicationController.getInstance().getDisplayManager().next(conf);    
        ApplicationController.getInstance().confirm(msg,callBack);
    }
    public static void display(String msg) {
//        if(alert == null) {
//            alert = new Alert("Info:");
//            alert.setTimeout(Alert.FOREVER);
//        }
//        
//        alert.setString(msg);        
//        
//        ApplicationController.getInstance().getDisplayManager().next(alert);
        ApplicationController.getInstance().displayMessage(msg);
    }
    
     public static void popup(String msg) {
//         if(alert == null)
//         {
//            alert = new Alert("Quick Info:");
//            alert.setTimeout(3000);
//         }
//        alert.setString(msg);
//        ApplicationController.getInstance().getDisplayManager().next(alert);
        ApplicationController.getInstance().popMessage(msg);
    }
}
