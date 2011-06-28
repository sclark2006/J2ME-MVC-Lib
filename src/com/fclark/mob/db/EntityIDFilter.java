/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import javax.microedition.rms.RecordFilter;

/**
 *
 * @author Frederick
 * @version 06/19/2011 01:46:53 AM
 */
public class EntityIDFilter implements RecordFilter {
    private int id;

    public EntityIDFilter(int id) throws Exception {
        this.id = id;
    }

    public boolean matches(byte[] candidate) {
        try {
            int candidateId = readInt(candidate);
            if (candidateId >= 0 && this.id == candidateId) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static int readInt(byte[] data) {
        if (data.length < 4) {
            return -1;
        }
        return (int) (((data[0] & 0xff) << 24) + ((data[1] & 0xff) << 16) + ((data[2] & 0xff) << 8) + (data[3] & 0xff));
    }
}
