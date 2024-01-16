package com.greensense.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ActionBuilder {

    private AbstractAction action;
    
    private ActionBuilder(String label, String actionCommand, ActionListener listener){

        action = new AbstractAction(label) {
            {
                putValue(AbstractAction.ACTION_COMMAND_KEY, actionCommand);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
            
        };

    }

    public static ActionBuilder createAction(String label, String actionCommand, ActionListener listener){
        return new ActionBuilder(label, actionCommand, listener);
    }

    public ActionBuilder shortDescription(String description){
        action.putValue(AbstractAction.SHORT_DESCRIPTION, description);
        return this;
    }

    public ActionBuilder longDescription(String description){
        action.putValue(AbstractAction.LONG_DESCRIPTION, description);
        return this;
    }

    public ActionBuilder smallIcon(ImageIcon smallIcon){
        action.putValue(AbstractAction.SMALL_ICON, smallIcon);
        return this;
    }

    public ActionBuilder largeIcon(ImageIcon largeIcon){
        action.putValue(AbstractAction.LARGE_ICON_KEY, largeIcon);
        return this;
    }

    public ActionBuilder mnemonic(int mnemonic){
        action.putValue(AbstractAction.MNEMONIC_KEY, mnemonic);
        return this;
    }

    public ActionBuilder putValue(String key, Object value){
        action.putValue(key, value);
        return this;
    }

    public AbstractAction build(){
        return action;
    }

}
