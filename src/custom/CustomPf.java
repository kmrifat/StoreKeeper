/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Rifat
 */
public class CustomPf {
    public void clearPassFieldByButton(PasswordField value, Button btn){
    btn.setVisible(false);
    
    
    
   
    value.setOnKeyReleased(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event) {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                btn.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                btn.setVisible(true);
            }
        }
    });
    btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            value.clear();
            btn.setVisible(false);
            value.requestFocus();
        }
    });
    }
}
