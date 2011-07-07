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
        if(ordinal < 0)
            throw new IllegalArgumentException("The ordinal number must be a positive number");
        
        if(getTypeName().equals("UNKNOWN"))
            throw new IllegalArgumentException("The specified field type is not allowed");
        
        this.ordinal = ordinal;        
        this.type = type;
    }

    public int ordinal() {
        return ordinal;
    }
    
    public int type() {
        return type;
    }
    
    public String getTypeName() {
        String result;
        switch(this.type) {
        case BYTE:
            result = "BYTE";
            break;
        case BOOLEAN:
            result = "BOOLEAN";
            break;
        case SHORT:
            result = "SHORT";
            break;
        case INT:
            result = "INT";
            break;
        case CHAR:
            result = "CHAR";
            break;
        case FLOAT:
            result = "FLOAT";
            break;
        case LONG:
            result = "LONG";
            break;
        case DOUBLE:
            result = "DOUBLE";
            break;
        case STRING:
            result = "STRING";
            break;
        case DATE:
            result = "DATE";
            break;
        case OBJECT:
            result = "OBJECT";
            break;
        default:
            result = "UNKNOWN";
            break;
        }
        
        return result;
    }
    public String toString() {
       return "Field[ordinal="+this.ordinal+", type="+getTypeName()+"]";
    }
}
