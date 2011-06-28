package com.fclark.mob.mvc;

import com.fclark.util.DisplayManager;

public abstract class Controller {

    private DisplayManager renderer;

    public Controller(DisplayManager renderer) {
        this.renderer = renderer;
    }

    public DisplayManager getDisplayManager() {
        return this.renderer;
    }

    public abstract void home();

    protected void render(View view) {
        renderer.next(view);
    }

    protected void render(View view, Object data) {
        view.write(data);
        renderer.next(view);
    }
    //public list(String search, String searchFields, Field orderBy, int order) {
}
