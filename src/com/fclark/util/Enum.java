/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.util;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Frederick
 * @version 06/25/2011 10:09:54 AM
 */
public abstract class Enum implements Comparable {
    private int ordinal;
    private String name;
    
    private static Hashtable enumsMap;
    
    static {
        enumsMap = new Hashtable();
    }
    
    private Enum() {
    }
    
    public Enum(String name,int ordinal) {
        this.ordinal = ordinal;
        this.name = name;
        List values = (List) enumsMap.get(name);
        if(values == null)
            values = new List();        
        values.add(this);        
        enumsMap.put(this.getClass().getName(), values);
        
    }
    
    public int ordinal() {
        return this.ordinal;
    }
    
    public String name() {
        return this.name;
    }
    
    //static
//    public static Enum valueOf(String name) {
//        throw new java.lang.IllegalArgumentException("This class must override this method.");
//    }
    
    public static Enum valueOf(Class enumClass, String name) {
        List values = (List)enumsMap.get(enumClass.getName());
        if(values == null)
            throw new IllegalArgumentException();        
        Enumeration en = values.elements();
        Enum result = null;
        while(en.hasMoreElements()) {
           result = (Enum)en.nextElement();
           if(result.name.equals(name) )
               break;
        }
        if(result == null)
            throw new IllegalArgumentException();
        return result;
    }
    
//    public static Enum[] values() {
//       throw new java.lang.IllegalArgumentException("You can't call this method from this class");
//    }
    
    protected static Object[] values(Class enumClass) {
       List values = (List)enumsMap.get(enumClass.getName());
       if(values == null)
           return null;
       return values.toArray();
    }
    
    public String toString() {
        return this.getClass().getName()+"."+this.name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enum other = (Enum) obj;
        if (this.ordinal != other.ordinal) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.ordinal;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

   

    public int compareTo(Object object) {
        if(object == null)
            throw new NullPointerException();
        Enum other = (Enum)object;
        return this.ordinal - other.ordinal;
    }
}
