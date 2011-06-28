/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import com.fclark.util.Collections;
import com.fclark.util.List;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.rms.*;

/**
 *
 * @author Frederick
 * @version 06/16/2011 07:00:59 PM
 */
public class Database {

    private static Hashtable storesTable;
    private final static EntitySerializator serializator;
    
    static {
        serializator = EntitySerializator.getInstance();
    }
    
    private static RecordStore getStore(String store) {
        if(storesTable == null)
            storesTable = new Hashtable();
        
        RecordStore recordStore = (RecordStore) storesTable.get(store);
        if(recordStore == null) {
            try {
                System.out.println("RecordStores: "+Collections.toString( RecordStore.listRecordStores())); 
                recordStore = RecordStore.openRecordStore(store, true);
                storesTable.put(store, recordStore);
            } catch (RecordStoreException ex) {}
        }
        return recordStore;
    }
    
    public static boolean createRecord(Storable entity) {
        try {
            RecordStore recordStore = getStore(entity.getStoreName());
            entity.setRecordId(recordStore.getNextRecordID());
            byte[] data = serializator.toBytes(entity);
            recordStore.addRecord(data, 0, data.length);
            ////////////////
            recordStore = null;
            data = null;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static boolean recordExists(Storable entity) {
                boolean result;
        try {
            if (getStore(entity.getStoreName()).getRecordSize(entity.getRecordId()) > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (RecordStoreException ex) {
            result = false;
        }

        return result;
    }
        
    
    public static int countRecords(String entityName) {
        try {
            return getStore(entityName).getNumRecords();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static List getRecordList(Class entityClass) {
        return getRecordList(entityClass,null,null);
    }
    public static List getRecordList(Class entityClass, RecordFilter filter, RecordComparator comparator) {
        List result;
        try {
        Storable entity = Entity.newInstance(entityClass);
        RecordEnumeration renum = getRecordEnumeration(entity.getStoreName(),filter,comparator);
        result = new List(renum.numRecords());
        
        while(renum.hasNextElement())  {
            entity = getRecord(entityClass, renum.nextRecordId());
            if(entity != null) result.add(entity);
            entity = null;
        }
        renum.destroy();
        renum = null;
        }catch(Exception ex) {
            result = Collections.EMPTY_LIST;
        }
        return result;
    }
    
    public static RecordEnumeration getRecordEnumeration(String entityName, 
            RecordFilter filter, RecordComparator comparator) {
        RecordEnumeration result;
        try {
            result = getStore(entityName).enumerateRecords(filter, comparator, false);
        }
        catch(Exception ex) {
            result = null;
        }
        return result;
    }
    
    public static RecordEnumeration getRecordEnumeration(String entityName) {
        return getRecordEnumeration(entityName, null, null);
    }
    
    public static Storable getFirstRecord(Class entityClass, RecordFilter filter,
            RecordComparator comparator) {
        Storable object;
        try {
            object = Entity.newInstance(entityClass);
            
            RecordEnumeration renum = getRecordEnumeration(object.getStoreName(), filter, comparator);
            if(renum.hasNextElement())
                object = getRecord(entityClass, renum.nextRecordId());
            else
                object = null;
            
        } catch (Exception ex) {
            object = null;
        }
        return object;
    }
        
    public static Storable getRecord(Class entityClass, int recordId) {
        try {
            Storable entity = Entity.newInstance(entityClass);
            entity.setRecordId(recordId);
            if(refreshRecord(entity))
                return entity;
            else
                return null;
        } catch (Exception ex) {
            return null;
        }
    }
    
    
    public static boolean refreshRecord(Storable entity) {        
        try {
            byte[] data = getStore(entity.getStoreName()).getRecord(entity.getRecordId());
            
            if(!serializator.fromBytes(entity, data))
                throw new RecordStoreException();
            data = null;
            return true;
        } catch (RecordStoreException ex) {
            return false;
        }
    }
    
    
    
    public static boolean updateRecord(Storable entity) {
        try {
            byte[] data = serializator.toBytes(entity);
            getStore(entity.getStoreName()).setRecord(entity.getRecordId(), data, 0, data.length);
            data = null;
            
        } catch (Exception de) {
            return false;
        }
        return true;
    }
    
     public static boolean deleteRecord(Storable entity) {
        try {
            getStore(entity.getStoreName()).deleteRecord(entity.getRecordId());            
        } catch (Exception de) {
            return false;
        }
        return true;
     }
     
     public static void close() {
         Enumeration stores = storesTable.elements();
         while(stores.hasMoreElements()) {
            try {
                ((RecordStore)stores.nextElement()).closeRecordStore();
            } catch (RecordStoreException ex) {
                
            }
         }
     }
}
