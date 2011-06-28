/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.loan.models;

import com.fclark.mob.db.Field;
import com.fclark.mob.db.Entity;

/**
 *
 * @author Frederick
 * @version 06/23/2011 02:21:51 PM
 */
public class PersonAudit extends Entity {

    public static final Field OPERATION = createField(PersonAudit.class, Field.CHAR),
            QUANTITY = createField(PersonAudit.class, Field.INT),
            LAST = createField(PersonAudit.class, Field.DATE);
}
