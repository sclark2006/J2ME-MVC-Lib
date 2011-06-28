package com.fclark.mob.mvc;

import com.fclark.mob.db.Entity;
import com.fclark.mob.db.Field;
import com.fclark.util.DisplayManager;
import com.fclark.util.List;

public abstract class CRUDController extends Controller {
    protected Entity currentItem;
    protected List items;
   
    public CRUDController(DisplayManager renderer) {
        super(renderer);
    }

    public abstract void create();
    public abstract void delete();
    public abstract void save();    
    public abstract void blank();
    public abstract void show();    
    public abstract void edit();    
    public abstract void list();
    public abstract void list(String search, Field searchField, Field orderBy, int order);

    public void back() {
        this.getDisplayManager().back();
    }
}
