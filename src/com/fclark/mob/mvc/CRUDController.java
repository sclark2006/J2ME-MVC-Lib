package com.fclark.mob.mvc;

import com.fclark.mob.db.Entity;
import com.fclark.mob.db.Field;
import com.fclark.mob.loan.Messages;
import com.fclark.util.Collections;
import com.fclark.util.ConfirmationCallBack;
import com.fclark.util.DisplayManager;
import com.fclark.util.List;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public abstract class CRUDController extends Controller {
    
    protected ListView listView;
    protected EditView editView;
    protected ShowView showView;
    protected Class entityClass;
    protected Entity currentItem;
    protected List items;
    
    public CRUDController(DisplayManager renderer, Class controlledEntity) {
        super(renderer);
        entityClass = controlledEntity;
        currentItem = null;
        items = null;
    }
    
    private void validateSelection() throws Exception {
        currentItem = (Entity) getListView().read(items);
        
        if (currentItem == null) {
            Messages.popup(Messages.MSG_NO_RECORD_SELECTED);
            throw new Exception();
        }
    }
    
    public Entity getCurrentItem() {
        currentItem = (Entity) getListView().read(items);
        return currentItem;
    }
    
    public ListView getListView() {
        if (listView == null) {
            listView = new ListView(Entity.getStoreName(entityClass) + " list", javax.microedition.lcdui.List.IMPLICIT);
            listView.setController(this);
        }
        return listView;
    }
    
    public EditView getEditView() {
        if (editView == null) {
            editView = new EditView(currentItem == null ? "Item's details" : currentItem.getStoreName() + "'s detail");
            editView.setController(this);
        }
        return editView;
    }
    
    public ShowView getShowView() {
        if (showView == null) {
            showView = new ShowView(currentItem == null ? "Item's details" : currentItem.getStoreName() + "'s detail");
            showView.setController(this);
        }
        return showView;
    }
    
    public void create() {
        if (currentItem == null) {
            currentItem = Entity.newInstance(entityClass);
        }
        
        currentItem = (Entity) getEditView().read(currentItem);
        Integer id = (Integer) Entity.max(entityClass, Entity.ID);
        currentItem.setId(id == null ? 1 : id.intValue() + 1);
        try {
            if (currentItem.create()) {
                Messages.display(Messages.MSG_SUCESSFULLY_CREATED);
                back();
            } else {
                Messages.popup(Messages.MSG_NOT_SAVED);
            }
        } catch (Exception e) {
            Messages.popup(e.getMessage());
        }
        
        
    }
    
    public void save() {
        currentItem = (Entity) getEditView().read(currentItem);
        try {
        if (currentItem.update()) {
            Messages.popup(Messages.MSG_SUCESSFULLY_SAVED);
        } else {
            Messages.popup(Messages.MSG_NOT_SAVED);
        }
        }catch(Exception ex) {
            Messages.popup(ex.getMessage());
        }
    }
    
    public void home() {
        list();
    }
    
    public void blank() {
        //if(currentItem != null && !currentItem.exists())
        //    if(Messages.confirm("Tiene cambios pendientes por grabar ¿Desea guardarlos?"))
        //        currentItem.save();
        currentItem = Entity.newInstance(entityClass);
        getEditView().clear();
        render(getEditView(), currentItem);
    }
    
    public void show() {
        try {
            validateSelection();
            render(getShowView(), currentItem);
        } catch (Exception e) {
        }
    }
    
    public void delete() {
        try {
            validateSelection();
            
            if (currentItem.delete()) {
                Messages.popup(Messages.MSG_RECORD_DELETED);
                refreshList();
                //list();
            } else {
                Messages.popup(Messages.MSG_RECORD_NO_DELETED);
                back();
            }
            
        } catch (Exception e) {
            
        }
        
    }
    
    public void deleteOld() {
        try {
            validateSelection();
            Messages.confirm(Messages.MSG_CONFIRM_DELETION, new ConfirmationCallBack() {
                
                public void confirm(boolean answer) {
                    if (answer) {
                        if (currentItem.delete()) {
                            Messages.popup(Messages.MSG_RECORD_DELETED);
                            list();
                        } else {
                            Messages.popup(Messages.MSG_RECORD_NO_DELETED);
                            back();
                        }
                    } else {
                        back();
                    }
                }
            });
            
        } catch (Exception e) {
        }
        
    }
    
    public void edit() {
        try {
            validateSelection();
            render(getEditView(), currentItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshList() {
        refreshList(Collections.ORDER_ASCENDING);
    }
    
    public void refreshList(int order) {
        items = Entity.findAll(entityClass, Entity.ID, order);
        if (items.size() > 0) {
            currentItem = (Entity) items.firstElement();
        }
    }
    
    public void list() {
        list(Collections.ORDER_ASCENDING);
    }
    
    public void list(int order) {
        items = Entity.findAll(entityClass, Entity.ID, order);
        if (items.size() > 0) {
            currentItem = (Entity) items.firstElement();
        }
        render(getListView(), items);
    }
    
    public void list(Field searchBy, Object value, Field orderBy, int order) {
        items = Entity.findBy(entityClass, searchBy, value, orderBy, order);
        if (items.size() > 0) {
            currentItem = (Entity) items.firstElement();
        }        
        render(getListView(), items);
    }
    
    public void back() {
        this.getDisplayManager().back();
        if (this.getListView().isShown()) {
            refreshList();
        }
    }
}
