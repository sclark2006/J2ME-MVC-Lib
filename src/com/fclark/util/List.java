/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.util;

import java.util.Vector;

/** 
 * 
 * @author Frederick
 * @version 06/18/2011 08:08:51 PM
 */
public class List extends Vector {

    public List() {
        super();
    }

    public List(int initialCapacity, int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
    }

    public List(int initialCapacity) {
        super(initialCapacity);
    }
    
    
    public List(Vector vector) {
        elementData = new Object[vector.size()];
        vector.copyInto(elementData);
        this.elementCount = elementData.length;
    }

    public List(Object[] array) {
        this.elementData = new Object[array.length];
        this.elementCount = array.length;
        System.arraycopy(array, 0, elementData, 0, elementCount);        
    }

    public boolean add(Object o) {
        try {
            super.addElement(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean add(int index,Object o) {
        try {            
            super.insertElementAt(o, index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean addAll(Vector vector) {
        try {
            super.ensureCapacity(this.size() + vector.size());
            for(int i=0; i < vector.size(); i++)
                this.addElement(vector.elementAt(i));        
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
    
    public boolean addAll(int index, Vector vector) {
        try {
            super.ensureCapacity((this.size() - index) + vector.size());
            for(int i=0; i < vector.size(); i++)
                this.insertElementAt(vector.elementAt(i), index);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
    
    public void clear() {
        super.removeAllElements();
    }
    
    public Object get(int index) {
        return super.elementAt(index);
    }
    
    public Object remove(int index) {
        Object obj = get(index);
        super.removeElementAt(index);
        return obj;
    }
    
    public boolean remove(Object o) {
        return super.removeElement(o);
    }
    
    public boolean removeAll(Vector vector) {
        //@TODO: Pending implementation
        return false;
    }
    
    public boolean retainAll(Vector vector) {
        //@TODO: Pending implementation
        return false;
    }
    
    public Object set(int index, Object element) {
        Object prev = get(index);
        super.setElementAt(element, index);
        return prev;
    }
    public List subList(int fromIndex, int toIndex) {
        Object[] array = new Object[(toIndex - fromIndex) + 1];
        System.arraycopy(elementData, fromIndex, array, 0, array.length);
        List result = new List(array);
        array = null;
        return result;
    }
    
    public Object[] toArray() {
        Object[] result = new Object[this.elementData.length];
        super.copyInto(result);
        return result;
    }
    
    public List sort(int order) {
        return (List) Collections.sort(this);
    }
    
    
}
