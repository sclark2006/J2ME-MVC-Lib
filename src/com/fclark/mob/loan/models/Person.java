/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.mob.loan.models;

/** 
 *
 * @author Frederick
 * @version 06/17/2011 08:38:55 PM
 */
import com.fclark.mob.db.Field;
import com.fclark.mob.db.Entity;
import com.fclark.util.Collections;
import com.fclark.util.List;
import com.fclark.util.TimeSpan;
import com.fclark.util.ValidationException;
import java.util.Date;

public class Person extends Entity {

    public static final Field NAME = createField(Person.class, Field.STRING),
            ADDRESS = createField(Person.class, Field.STRING),
            PHONE = createField(Person.class, Field.STRING),
            EMAIL = createField(Person.class, Field.STRING),
            BIRTHDATE = createField(Person.class, Field.DATE),
            BALANCE = createField(Person.class, Field.DOUBLE);
    
    private transient List loans = null;
    
    public Person() {
        super("People");
        long millis18back =  (long)(new Date().getTime() -  (long)(TimeSpan.MILLIS_PER_YEAR * 18));
        set(BIRTHDATE, new Date(millis18back));
        setDouble(BALANCE, 0.0d);        
    }
    
    private void loadLoans() {
        loans = Loan.findBy(Loan.PERSON_ID, this.get(ID));
    }
    // ////////////////////////////////
    public List getLoans() {
        if(loans == null)
            loadLoans();
        return loans;
    }
    
    public void setLoans(List loans) {
        this.loans = loans;
    }
    
    public void addLoan(Loan loan) {
        if(loan != null) {
            loan.set(Loan.PERSON_ID, this.get(ID));
            this.loans.add(loan);
        }
    }
    
    public void updateLoan(Loan loan) {
        int index = Collections.search(loans, loan);
        if(index >= 0)
            this.loans.set(index, loan);
    }
       
    public boolean create() {
        validateAge();
        boolean created = super.create();
        if(created && loans != null && loans.size() > 0) {
            Entity.setAll(loans, Loan.PERSON_ID, this.get(ID));
            Entity.saveAll(loans);
            loadLoans();
        }
        
        return created;
    }
    private void validateAge() throws ValidationException {
        if(this.age() > 0 && this.age() < 18 )
            throw new ValidationException("The person must be at least 18 years old");
    }

    public boolean update() {
        validateAge();
        boolean updated = super.update();
        if(loans != null && loans.size() > 0) {
            Entity.setAll(loans, Loan.PERSON_ID, this.get(ID));
            Entity.saveAll(loans);
            loadLoans();
        }
        return updated;
    }       
    
    public int age() {
        TimeSpan age = TimeSpan.between(new Date(), this.getDate(BIRTHDATE));
        if (age == null) {
            return 1;
        }
        return age.getYears();
    }
        

    public String toString() {
        return getInt(ID) + " " + get(NAME) + " " + age();
    }

    // <editor-fold defaultstate="collapsed" desc=" Static Query Methods">
    public static int count() {
        return Entity.count(getStoreName(Person.class));
    }

    public static List findAll() {
        return Entity.findAll(Person.class);
    }

    public static List findAll(Field orderBy, int sortType) {
        return Entity.findAll(Person.class, orderBy, sortType);
    }

    public static Person findById(int id) {
        return (Person) Entity.findById(Person.class, id);
    }

    public static Person findByRecordId(int recordId) {
        return (Person) Entity.findByRecordId(Person.class, recordId);
    }

    public static List findBy(Field field, Object value) {
        return Entity.findBy(Person.class, field, value);
    }

    public static List findBy(Field field, Object value, Field orderBy, int sortType) {
        return Entity.findBy(Person.class, field, value, orderBy, sortType);
    }

    public static Object min(Field field) {
        return Entity.min(Person.class, field);
    }

    public static Object max(Field field) {
        return Entity.max(Person.class, field);
    }

    public static Person first() {
        return (Person) Entity.first(Person.class, ID);
    }

    public static Person first(Field field) {
        return (Person) Entity.first(Person.class, field);
    }

    public static Person last() {
        return (Person) Entity.last(Person.class, ID);
    }

    public static Person last(Field field) {
        return (Person) Entity.last(Person.class, field);
    }
    // </editor-fold>     
}
