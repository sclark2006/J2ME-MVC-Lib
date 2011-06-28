
package com.fclark.util;

/**
 *
 * @author Frederick
 */
import com.fclark.mob.db.Field;
import java.util.Date;
import java.util.NoSuchElementException;

public interface AccessibleByField {

    Date getDate(Field field) throws NoSuchElementException;

    String getString(Field field) throws NoSuchElementException;

    float getFloat(Field field) throws NoSuchElementException;

    double getDouble(Field field) throws NoSuchElementException;

    boolean getBoolean(Field field) throws NoSuchElementException;

    char getChar(Field field) throws NoSuchElementException;

    long getLong(Field field) throws NoSuchElementException;

    short getShort(Field field) throws NoSuchElementException;

    int getInt(Field field) throws NoSuchElementException;

    byte getByte(Field field) throws NoSuchElementException;

    Object get(Field field) throws NoSuchElementException;

    void set(Field field, Object value) throws NoSuchElementException;

    void setFloat(Field field, float value) throws NoSuchElementException;

    void setDouble(Field field, double value) throws NoSuchElementException;

    void setBoolean(Field field, boolean value) throws NoSuchElementException;

    void setChar(Field field, char value) throws NoSuchElementException;

    void setLong(Field field, long value) throws NoSuchElementException;

    void setInt(Field field, int value) throws NoSuchElementException;

    void setShort(Field field, short value) throws NoSuchElementException;

    void setByte(Field field, byte value) throws NoSuchElementException;
}
