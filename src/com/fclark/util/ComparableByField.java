package com.fclark.util;

import com.fclark.mob.db.Field;

public interface ComparableByField extends Comparable, AccessibleByField {	
    int compareTo(Object object, Field field);
    //int compareTo(Object object, int field);
}
