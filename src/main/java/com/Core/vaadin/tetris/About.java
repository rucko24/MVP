package com.Core.vaadin.tetris;

import com.Core.vaadin.Core;
import com.vaadin.ui.Window;


/**
 *
 * @author Matti Tahvonen
 */
public class About extends Window {

    public About() {
        setCaption("Server side Tetris game, WTF!?");
        
        Core.getCurrent().addWindow(this);
    }
    
}
