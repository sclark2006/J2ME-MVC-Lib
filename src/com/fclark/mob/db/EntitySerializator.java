/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Frederick
 * @version 06/17/2011 09:03:39 PM
 */
public class EntitySerializator {
    private static EntitySerializator instance = null;
    private EntitySerializator() {        
    }
    
    public byte[] toBytes(Storable store) {
        byte[] result;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(baos);        
        Field[] md = Entity.getMetaData(store.getClass()); //store.getMetaData();
        try {
            for (int i = 0; i < md.length; i++) {
                Field field = md[i];
                switch (field.type()) {
                    case Field.STRING:
                        output.writeUTF(store.getString(field));
                        break;
                    case Field.DATE:
                        output.writeLong(store.getDate(field).getTime());
                        break;
                    case Field.BOOLEAN:
                        output.writeBoolean(store.getBoolean(field));
                        break;
                    case Field.CHAR:
                        output.writeChar(store.getChar(field));
                        break;
                    case Field.INT:
                        output.writeInt(store.getInt(field));
                        break;
                    case Field.BYTE:
                        output.writeByte(store.getByte(field));
                        break;
                    case Field.SHORT:
                        output.writeShort(store.getShort(field));
                        break;
                    case Field.LONG:
                        output.writeLong(store.getLong(field));
                        break;
                    case Field.FLOAT:
                        output.writeFloat(store.getFloat(field));
                        break;
                    case Field.DOUBLE:
                        output.writeDouble(store.getDouble(field));
                        break;
                    default:
                        throw new java.util.NoSuchElementException();
                }// switch			
            }//for
            result = baos.toByteArray();
        } catch (IOException ioe) {
            result = null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (Exception ae) {
                    result = null;
                }
            }
        }

        md = null;
        baos = null;
        output = null;
        return result;
    }//toBytes

    public boolean fromBytes(Storable store, byte[] data) {
        boolean result;
        DataInputStream input;
        input = new DataInputStream(new ByteArrayInputStream(data));
        Field[] md =  Entity.getMetaData(store.getClass());//store.getMetaData();
        try {
            for (int i = 0; i < md.length; i++) {
                Field field = md[i];
                switch (field.type()) {
                    case Field.STRING:
                        store.set(field, input.readUTF());
                        break;
                    case Field.DATE:
                        store.set(field, new java.util.Date(input.readLong()));
                        break;
                    case Field.BOOLEAN:
                        store.setBoolean(field, input.readBoolean());
                        break;
                    case Field.CHAR:
                        store.setChar(field, input.readChar());
                        break;
                    case Field.INT:
                        store.setInt(field, input.readInt());
                        break;
                    case Field.BYTE:
                        store.setByte(field, input.readByte());
                        break;
                    case Field.SHORT:
                        store.setShort(field, input.readShort());
                        break;
                    case Field.LONG:
                        store.setLong(field, input.readLong());
                        break;
                    case Field.FLOAT:
                        store.setFloat(field, input.readFloat());
                        break;
                    case Field.DOUBLE:
                        store.setDouble(field, input.readDouble());
                        break;
                    default:
                        throw new java.util.NoSuchElementException();
                }// switch			
            }//for

            result = true;
        } catch (IOException ioe) {
            result = false;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception ae) {
                    result = false;
                }
            }
        }//finally
        md = null;
        input = null;
        return result;
    } //fromBytes
    
    
    public static EntitySerializator getInstance() {
        if(instance == null)
            instance = new EntitySerializator();
        return instance;
    }
}//class
