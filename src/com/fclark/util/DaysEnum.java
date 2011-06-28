/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.util;

/**
 *
 * @author Frederick
 * @version 06/25/2011 06:36:22 PM
 */
public class DaysEnum extends Enum {

    public static final DaysEnum SUNDAY = new DaysEnum("SUNDAY", 0),
            MONDAY = new DaysEnum("MONDAY", 1);
    //static DaysEnum[] $VALUES = new DaysEnum[]{SUNDAY, MONDAY};

    public DaysEnum(String name, int ordinal) {
        super(name, ordinal);
    }

    public static DaysEnum[] values() { 
        return (DaysEnum[]) values(DaysEnum.class);
        //return (DaysEnum[]) $VALUES.clone();
    }

    public static DaysEnum valueOf(String name) {
        return (DaysEnum) Enum.valueOf(null, name);
    }
}