package com.fclark.mob.db;

import com.fclark.util.Collections;
import com.fclark.util.List;
import java.util.Hashtable;

public abstract class Entity extends AbstractRecord {

    private static final Hashtable $MD_MAP = new Hashtable();
    private static String MSG_ILLEGAL_CALL = "You can't call this method from this class";
    public static final Field ID = new Field(0, Field.INT);
    private String entityName = null;

    public Entity() {
        setStoreName(getStoreName(this.getClass()));
    }
    
    public Entity(String name) {
        setStoreName(name);
    }
    
    protected final void setStoreName(String name) throws IllegalArgumentException {
        if (this.entityName != null) {
            throw new IllegalArgumentException("Entity name already defined!");
        }
        if (name.length() > 32) {
            throw new IllegalArgumentException("Entity name too long. Should not be longer than 32 characters");
        }
        this.entityName = name;
    }

    public String getStoreName() {
        if (this.entityName == null) {
            this.entityName = getStoreName(this.getClass());
        }
        return this.entityName;
    }

    public void setId(int id) {
        this.setInt(ID, id);
    }

    public int getId() {
        return this.getInt(ID);
    }

    public boolean create() {
        return Database.createRecord(this);

    }

    public boolean update() {
        return Database.updateRecord(this);
    }
    
    public boolean exists() {
        return Database.recordExists(this);
    }//exists

    public boolean save() {
        boolean result;
        if (this.getRecordId() > 0) {
            result = update();
            if(result == false)
                result = create();
        }
        else
            result = create();
        
        return result;
    }

    public boolean delete() {
        return Database.deleteRecord(this);
    }

    public Entity refresh() {
        Database.refreshRecord(this);
        return this;
    }

    // ///////////////////////////////////////////////////////////
    public int compareTo(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }

        int result;
        Entity entity = (Entity) object;
        try {
            if (this.getInt(ID) > 0 && entity.getInt(ID) > 0) {
                result = this.getInt(ID) - entity.getInt(ID);
            } else {
                result = this.getRecordId() - entity.getRecordId();
            }
        } catch (Exception e) {
            result = this.getRecordId() - entity.getRecordId();
        }
        entity = null;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Entity other = (Entity) obj;
        if (this.getRecordId() != other.getRecordId()) {
            other = null;
            return false;
        }
        if (this.getInt(ID) != other.getInt(ID)) {
            other = null;
            return false;
        }
        other = null;
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        try {
            if (getRecordId() > 0) {
                result = prime * result + getRecordId();
            } else {
                result = prime * result + this.getInt(ID);
            }
        } catch (Exception e) {
            result = prime * result + ((Object) data).hashCode();
        }
        return result;
    }

    //Static Methods
    protected synchronized static Field createField(Class entityClass, int type) {
        Field result;
        List fields = (List) $MD_MAP.get(entityClass);
        if (fields == null) {
            fields = new List();
            fields.add(ID);
        }
        int ordinal = fields.size() + 1;
        result = new Field(ordinal, type);
        fields.add(result);
        $MD_MAP.put(entityClass, fields);
        fields = null;
        return result;
    }

    public static Field[] getMetaData() {
        throw new java.lang.IllegalArgumentException("You must override this method in this subclass of Entity");
    }

    public static Field[] getMetaData(Class entityClass) {
        Field[] result = null;
        List fields = (List) $MD_MAP.get(entityClass);
        if (fields == null) {
            fields = new List();
        }
        result = new Field[fields.size()];
        fields.copyInto(result);
        fields = null;
        return result;
    }

    // <editor-fold defaultstate="collapsed" desc=" Static Database Operation Methods"> 
    public static Entity newInstance(Class entityClass) {
        if (Entity.class.isAssignableFrom(entityClass)) {
            try {
                return (Entity) entityClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassCastException(e.getMessage());
            }
        } else {
            throw new ClassCastException();
        }
    }

    public static String getStoreName(Class entityClass) {
        String entityName = entityClass.getName();
        int dot = entityName.lastIndexOf('.');
        if (dot > 0) {
            entityName = entityName.substring(dot + 1);
        }

        if (entityName.length() > 32) {
            entityName = entityName.substring(0, 30).concat("_");
        }

        return entityName;
    }

    public static int count() {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static int count(String entityName) {
        return Database.countRecords(entityName);
    }

    public static List findAll() {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static List findAll(Class entityClass) {
        return Database.getRecordList(entityClass);
    }

    public static List findAll(Field orderBy, int sortType) {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static List findAll(Class entityClass, Field orderBy, int sortType) {
        try {
            return Database.getRecordList(entityClass, null, new EntityFieldComparator(entityClass, orderBy, sortType));
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }

//    public static Entity findById(int id) {
//        throw new java.lang.IllegalArgumentException("You can't call this method from this class");
//    }
    protected static Entity findById(Class entityClass, int id) {
        try {
            return (Entity) Database.getFirstRecord(entityClass, new EntityIDFilter(id), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    public static Entity findByRecordId(int id) {
//        throw new java.lang.IllegalArgumentException("You can't call this method from this class");
//    }
    protected static Entity findByRecordId(Class entityClass, int id) {
        try {
            return (Entity) Database.getRecord(entityClass, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object max(Field field) {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    public static Object min(Field field) {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static Object max(Class entityClass, Field field) {
        Entity entity = last(entityClass, field);
        if (entity == null) {
            return null;
        }
        return entity.get(field);
    }

    protected static Object min(Class entityClass, Field field) {
        Entity entity = first(entityClass, field);
        if (entity == null) {
            return null;
        }
        return entity.get(field);
    }

    protected static Entity first(Class entityClass, Field orderBy) {
        try {
            return (Entity) Database.getFirstRecord(entityClass, null,
                    new EntityFieldComparator(entityClass, orderBy, Collections.ORDER_ASCENDING));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected static Entity last(Class entityClass, Field orderBy) {
        try {
            return (Entity) Database.getFirstRecord(entityClass, null,
                    new EntityFieldComparator(entityClass, orderBy, Collections.ORDER_DESCENDING));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List findBy(Field field, Object value) {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static List findBy(Class entityClass, Field field, Object value) {
        try {
            return Database.getRecordList(entityClass, new EntityFieldFilter(entityClass, field, value), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public static List findBy(Field field, Object value, Field orderBy, int sortType) {
        throw new java.lang.IllegalArgumentException(MSG_ILLEGAL_CALL);
    }

    protected static List findBy(Class entityClass, Field field, Object value, Field orderBy, int sortType) {
        try {
            return Database.getRecordList(entityClass, new EntityFieldFilter(entityClass, field, value),
                    new EntityFieldComparator(entityClass, orderBy, sortType));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
    
    public static void setAll(List entityList, Field field, Object value) {
        for(int i=0; i < entityList.size(); i++)
            ((Entity)entityList.get(i)).set(field, value);
    }
    
    public static boolean saveAll(List entityList) {
        boolean result = true;
        for(int i=0; i < entityList.size(); i++)
            if(!((Entity)entityList.get(i)).save());
                result = false;
        return result;
    }
    
    public static boolean deleteAll(List entityList) {
        boolean result = true;
        for(int i=0; i < entityList.size(); i++)
            if(!((Entity)entityList.get(i)).delete());
                result = false;
        return result;
    }
    // </editor-fold>        
}//class
