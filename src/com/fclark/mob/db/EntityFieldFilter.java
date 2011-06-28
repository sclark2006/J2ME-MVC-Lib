/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import com.fclark.util.Comparator;
import com.fclark.util.DateComparator;
import javax.microedition.rms.RecordFilter;

/**
 *
 * @author Frederick
 * @version 06/19/2011 12:59:00 AM
 */
public class EntityFieldFilter implements RecordFilter {
    private Field field;
    private EntitySerializator serializator;
    private Entity entity;
    private Object value;
    
    public EntityFieldFilter(Class entityClass, Field field, Object value) 
            throws Exception
    {
        this.field = field;
        this.value = value;
        this.serializator = EntitySerializator.getInstance();
        this.entity = (Entity)entityClass.newInstance();
    }

    public boolean matches(byte[] candidate) {
        if(serializator.fromBytes(entity, candidate)) {            
            Object storedValue = entity.get(field);
            if(value == null && storedValue == null)
                return true;
            else  if(field.type() == Field.DATE) {
                Comparator dcomp = DateComparator.getInstance();
                if(dcomp.compare(storedValue, value) == 0)
                    return true;
                else
                    return false;
            } else 
                return storedValue.equals(value);            
        }
        else
            return false;
    }
    
}
