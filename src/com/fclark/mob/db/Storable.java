package com.fclark.mob.db;

import com.fclark.util.ComparableByField;

/**
 * 
 * @author Frederick
 * @version  17/Jun/2011
 */
public interface Storable extends ComparableByField {

    void setRecordId(int id);

    int getRecordId();

    String getStoreName();

    //Field[] getMetaData();
}
