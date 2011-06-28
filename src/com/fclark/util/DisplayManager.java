package com.fclark.util;
import java.util.Stack;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

import com.fclark.mob.mvc.View;


public class DisplayManager {

	private View current;
	private Display display;
	private Stack	stack;
	
	/**
	 * Creates a display manager associated to
	 * the specified display.
	 * 
	 * @param display target display.
	 */
	public DisplayManager(Display display) {
		if (display == null) {
			throw new IllegalArgumentException("Display can not be null.");
		}
		this.display = display;
		this.stack	 = new Stack();		
	}
	
	/**
	 * Sets the specified displayable as the current
	 * screen.
	 * 
	 * @param next screen to show.
	 */
        
        public void show(Displayable disp) {
            this.display.setCurrent(disp);
        }
        
	public void next(View next) {
		if (this.current == next) {
			return;
		}
		
		if (this.current != null) {
			this.stack.push(this.current);
		}
		this.current = next;
		this.display.setCurrent(this.current.asDisplayable());
	}
	
	/**
	 * Goes back to the last screen.
	 */
	public void back() {
		if (this.stack.size() > 0) {
			this.current = (View) this.stack.pop();
			this.display.setCurrent(this.current.asDisplayable());
		}
	}
	
}
