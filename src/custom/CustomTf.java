/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author Rifat
 */
public class CustomTf {

    public void clearTextFieldByButton(TextField value, Button btn) {



        btn.setVisible(false);
        btn.setText(null);
        btn.minHeight(35.0);
        btn.minHeight(25.0);
        btn.getStylesheets().add("");
        value.setOnKeyReleased((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                btn.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                btn.setVisible(true);
            }
        });
        value.setOnKeyTyped((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                btn.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                btn.setVisible(true);
            }
        });
        value.setOnKeyPressed((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                btn.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                btn.setVisible(true);
            }
        });
        btn.setOnAction((ActionEvent event) -> {
            value.clear();
            btn.setVisible(false);
            value.requestFocus();
        });

    }

    public void numaricTextfield(TextField tField) {
        tField.setOnKeyReleased((KeyEvent event) -> {
            if (!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }
        });
        tField.setOnKeyPressed((KeyEvent event) -> {
            if (!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }
        });
        tField.setOnKeyTyped((KeyEvent event) -> {
            if (!tField.getText().matches("[0-9.]*")) {
                tField.deletePreviousChar();
            }
        });
    }
}
