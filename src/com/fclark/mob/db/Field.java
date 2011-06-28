package com.fclark.mob.db;

public class Field {

    public static final int BYTE = 0x0;
    public static final int BOOLEAN = 0x1;
    public static final int SHORT = 0x2;
    public static final int INT = 0x3;
    public static final int CHAR = 0x4;
    public static final int FLOAT = 0x5;
    public static final int LONG = 0x6;
    public static final int DOUBLE = 0x7;
    public static final int STRING = 0x8;
    public static final int DATE = 0x9;
    public static final int OBJECT = 0xA;
            
    private int ordinal;
    private int type;
    
   
    Field(int ordinal, int type) {
        this.ordinal = ordinal;
        this.type = type;
    }

    public int ordinal() {
        return ordinal;
    }
    
    public int type() {
        return type;
    }    
}
