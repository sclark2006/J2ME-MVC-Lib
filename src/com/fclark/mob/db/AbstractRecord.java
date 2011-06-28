/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import com.fclark.util.Comparator;
import com.fclark.util.DateComparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 *
 * @author Frederick
 * @version 06/20/2011 07:14:14 PM
 */
public abstract class AbstractRecord implements Storable {
    
    private int recordId;
    protected Object[] data;
    //protected Field[] metaData;

    public void setRecordId(int id) {
        this.recordId = id;
    }

    public int getRecordId() {
        return recordId;
    }

    // ///////////////////////////////////////////////////////////   
    public int compareTo(Object object, Field field) {
        if (object == null) {
            throw new NullPointerException();
        }

        int result;
        Entity entity = (Entity) object;
        switch (field.type()) {
            case Field.STRING:
                result = this.getString(field).compareTo(entity.getString(field));
                break;
            case Field.DATE:
                Comparator cmp = DateComparator.getInstance();
                result = cmp.compare(this.get(field), entity.get(field));
                cmp = null;
                break;
            case Field.BOOLEAN:
                result = this.getBoolean(field) == entity.getBoolean(field) ? 0
                        : this.getBoolean(field) ? 1 : -1;
                break;
            case Field.CHAR:
                result = (int) (this.getChar(field) - entity.getChar(field));
                break;
            case Field.INT:
                result = this.getInt(field) - entity.getInt(field);
                break;
            case Field.BYTE:
                result = (int) (this.getByte(field) - entity.getByte(field));
                break;
            case Field.SHORT:
                result = (int) (this.getShort(field) - entity.getShort(field));
                break;
            case Field.LONG:
                result = (int) (this.getLong(field) - entity.getLong(field));
                break;
            case Field.FLOAT:
                result = (int) (this.getFloat(field) - entity.getFloat(field));
                break;
            case Field.DOUBLE:
                result = (int) (this.getDouble(field) - entity.getDouble(field));
                break;
            default:
                throw new NoSuchElementException();
        }// switch
        entity = null;
        return result;
    }

    // /////////////////////////////////////////////////////
    //////////////////////
//    public Field[] getMetaData() {
//        return metaData;
//    }
    public Object get(Field field) throws NoSuchElementException {
        try {
            return data[field.ordinal()];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException();
        }
    }

    public void set(Field field, Object value) throws NoSuchElementException {
        try {
            data[field.ordinal()] = value;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new NoSuchElementException();
        }
    }

    //
    public int getInt(Field field) throws NoSuchElementException {
        int result;
        try {
            result = ((Integer) get(field)).intValue();
        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    public Date getDate(Field field) throws NoSuchElementException {
        return (Date) get(field);
    }

    public String getString(Field field) throws NoSuchElementException {
        return (String) get(field);
    }

    public float getFloat(Field field) throws NoSuchElementException {
        float result;
        try {
            result = ((Float) get(field)).floatValue();
        } catch (Exception ex) {
            result = -1f;
        }
        return result;
    }

    public double getDouble(Field field) throws NoSuchElementException {
        double result;
        try {
            result = ((Double) get(field)).doubleValue();
        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    public boolean getBoolean(Field field) throws NoSuchElementException {
        boolean result;
        try {
            result = ((Boolean) get(field)).booleanValue();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public char getChar(Field field) throws NoSuchElementException {
        char result;
        try {
            result = ((Character) get(field)).charValue();
        } catch (Exception ex) {
            result = 0x00;
        }
        return result;
    }

    public long getLong(Field field) throws NoSuchElementException {
        long result;
        try {
            result = ((Long) get(field)).longValue();
        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    public short getShort(Field field) throws NoSuchElementException {
        short result;
        try {
            result = ((Short) get(field)).shortValue();
        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    public byte getByte(Field field) throws NoSuchElementException {
        byte result;
        try {
            result = ((Byte) get(field)).byteValue();
        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    // Setters
    public void setFloat(Field field, float value)
            throws NoSuchElementException {
        set(field, new Float(value));
    }

    public void setDouble(Field field, double value)
            throws NoSuchElementException {
        set(field, new Double(value));
    }

    public void setBoolean(Field field, boolean value)
            throws NoSuchElementException {
        set(field, new Boolean(value));
    }

    public void setChar(Field field, char value) throws NoSuchElementException {
        set(field, new Character(value));
    }

    public void setLong(Field field, long value) throws NoSuchElementException {
        set(field, new Long(value));
    }

    public void setShort(Field field, short value)
            throws NoSuchElementException {
        set(field, new Short(value));
    }

    public void setInt(Field field, int value) throws NoSuchElementException {
        set(field, new Integer(value));
    }

    public void setByte(Field field, byte value) throws NoSuchElementException {
        set(field, new Byte(value));
    }
}
