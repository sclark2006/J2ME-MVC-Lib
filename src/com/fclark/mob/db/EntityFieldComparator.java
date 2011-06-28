/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import com.fclark.util.Collections;
import javax.microedition.rms.RecordComparator;

/**
 *
 * @author Frederick
 * @version 06/19/2011 04:02:33 PM
 */
public class EntityFieldComparator implements RecordComparator {
    private Field field;
    private Class entityClass;
    private EntitySerializator serializator;
    private Entity entity1;
    private Entity entity2;
    private int sortType;
    
    public EntityFieldComparator(Class entityClass, Field field) throws Exception
    {
       this(entityClass, field, Collections.ORDER_ASCENDING);
    }

    public EntityFieldComparator(Class entityClass, Field field, int sortType) throws Exception
    {
       this.field = field;
       this.serializator = EntitySerializator.getInstance();
       this.entity1 = Entity.newInstance(entityClass);
       this.entity2 = Entity.newInstance(entityClass);
       this.sortType = sortType;
    }
    
    public int compare(byte[] rec1, byte[] rec2) {
        if(serializator.fromBytes(entity1, rec1) &&
           serializator.fromBytes(entity2, rec2) ) {
            int order = entity1.compareTo(entity2, field);
            
            
            if(order < 0) 
                order = PRECEDES;
            else if(order > 0) 
                order = FOLLOWS;
            else order = EQUIVALENT;
            
            if(sortType == Collections.ORDER_DESCENDING)
                order *= Collections.ORDER_DESCENDING;
            return order;
        }
        else
            return EQUIVALENT;
    }   
}
