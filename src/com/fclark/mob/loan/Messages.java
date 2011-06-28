package com.fclark.mob.loan;

import com.fclark.mob.loan.controllers.ApplicationController;

public class Messages {
    public static final String MSG_HAPPY_BIRTHDAY = "!Feliz cumplea�os para ";
    public static final String MSG_NO_BIRTHDATES_TODAY = "No hay cumplea�os para el d�a de hoy!";
    public static final String MSG_RECORD_DELETED = "El registro ha sido eliminado!";
    public static final String MSG_SPECIFY_NAME = "Debe especificar el nombre de la persona";
    public static final String MSG_SPECIFY_AGE = "Debe especificar la edad de la persona";
    public static final String MSG_IS_ADULT = "La persona debe tener 18 a�os o m�s";
    public static final String MSG_SUCESSFULLY_CREATED = "Registro creado satisfactoriamente!";
    public static final String MSG_SUCESSFULLY_SAVED = "Cambios guardados satisfactoriamente!";
    public static final String MSG_NOT_SAVED = "El registro no pudo ser guardado!";
    
    public static void displayMessage(String msg) {
        ApplicationController.getInstance().displayMessage(msg);
    }
    
     public static void popMessage(String msg) {
        ApplicationController.getInstance().popMessage(msg);
    }
}
