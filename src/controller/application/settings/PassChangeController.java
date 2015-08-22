/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import custom.*;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import media.userNameMedia;
import DAL.Users;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class PassChangeController implements Initializable {

    Users users = new Users();

    @FXML
    private PasswordField pfCurrentPass;
    @FXML
    private PasswordField pfNewPass;
    @FXML
    private PasswordField pfRePass;
    @FXML
    private Button btnClrCurrentPf;
    @FXML
    private Button btnClrRePass;
    @FXML
    private Button btnClrNewPass;
    @FXML
    private Button btnChangePass;
    @FXML
    private Button btnClose;

    private String userId;
    private String userName;
    private userNameMedia nameMedia;

    @FXML
    private ImageView imgCurrentPassMatch;
    @FXML
    private ImageView imgNewPassMatch;

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        userName = nameMedia.getUsrName();
        this.nameMedia = nameMedia;
    }

    CustomPf customPf = new CustomPf();

    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customPf.clearPassFieldByButton(pfCurrentPass, btnClrCurrentPf);
        customPf.clearPassFieldByButton(pfNewPass, btnClrNewPass);
        customPf.clearPassFieldByButton(pfRePass, btnClrRePass);

        BooleanBinding binding = pfCurrentPass.textProperty().isEmpty()
                .or(pfNewPass.textProperty().isEmpty()
                        .or(pfRePass.textProperty().isEmpty()));

        btnChangePass.disableProperty().bind(binding);

    }

    @FXML
    private void btnChangePassOnAction(ActionEvent event) {
        if (isCurrentPasswordChecqOk()) {
            if (isPasswordMatch()) {
                updatePassword();
            }

        } else {
            System.out.println("ddd");
        }

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pfOnAction(ActionEvent event) {
        btnChangePassOnAction(event);
    }

    @FXML
    private void pfNewPassWordMatch(KeyEvent event) {
        if (pfNewPass.getText().matches(pfRePass.getText())) {
            System.out.println("Match");
        } else {
            Action aDialog = Dialogs.create()
                    .title("Error")
                    .actions(Dialog.ACTION_CLOSE)
                    .message("Invalid password")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .showError();
        }
    }

    private boolean isCurrentPasswordChecqOk() {
        boolean conDitionValid = true;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from User where Id=? and Password=?");
            pst.setString(1, userId);
            pst.setString(2, pfCurrentPass.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Old Password Match");
                return conDitionValid;
            }
            Action aDialog = Dialogs.create()
                    .title("Error")
                    .actions(Dialog.ACTION_CLOSE)
                    .message("Invalid Password")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .showError();
            conDitionValid = false;

        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conDitionValid;
    }

    private boolean isPasswordMatch() {
        boolean passMatch;
        if (pfNewPass.getText().matches(pfRePass.getText())) {
            System.out.println("New Password match");
            passMatch = true;

        } else {
            Action aDialog = Dialogs.create()
                    .title("Error")
                    .actions(Dialog.ACTION_CLOSE)
                    .message("New Password what you enterd are not matched")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .showError();
            passMatch = false;
        }
        return passMatch;
    }

    private void updatePassword() {

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Update User set Password=? where Id=?");
            pst.setString(1, pfNewPass.getText());
            pst.setString(2, userId);
            pst.executeUpdate();

            Action aDialog = Dialogs.create()
                    .title("Error")
                    .actions(Dialog.ACTION_CLOSE)
                    .message("Sucess.....")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .showWarning();
            if (aDialog == Dialog.ACTION_CLOSE) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
