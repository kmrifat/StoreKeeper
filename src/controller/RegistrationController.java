/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Getway.UsersGetway;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import custom.*;
import dataBase.SQL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import DAL.Users;
import dataBase.DBProperties;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class RegistrationController implements Initializable {

    @FXML
    private Hyperlink hlLogin;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private PasswordField pfUserPassword;
    @FXML
    private PasswordField pfReUserPassword;
    @FXML
    private Button btnClearUserName;
    @FXML
    private Button btnClearFullName;
    @FXML
    private Button btnClearPass;
    @FXML
    private Button btnClearRePass;
    @FXML
    private Button btnSignUp;

    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    private Stage stage;

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomPf cPF = new CustomPf();
        CustomTf cTF = new CustomTf();

        cTF.clearTextFieldByButton(tfUserName, btnClearUserName);
        cTF.clearTextFieldByButton(tfFullName, btnClearFullName);
        cPF.clearPassFieldByButton(pfUserPassword, btnClearPass);
        cPF.clearPassFieldByButton(pfReUserPassword, btnClearRePass);

        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(tfFullName.textProperty().isEmpty()
                        .or(pfUserPassword.textProperty().isEmpty())
                        .or(pfReUserPassword.textProperty().isEmpty()));

        btnSignUp.disableProperty().bind(boolenBinding);
    }

    @FXML
    private void hlLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setMaximized(true);
        nStage.setTitle("Registration -StoreKeeper");
        nStage.show();

        Stage hlLoginStage = (Stage) hlLogin.getScene().getWindow();
        hlLoginStage.close();
    }

    @FXML
    private void btnRegistration(ActionEvent event) {
        SQL sql = new SQL();
        if (isValidCondition()) {
            users.userName = tfUserName.getText();
            users.fullName = tfUserName.getText();
            users.password = pfUserPassword.getText();
            usersGetway.save(users);
            sql.basicPermission(tfUserName.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Login now");
            alert.setContentText("You admin account has been create sucessfully \n to login now click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    hlLogin(event);
                } catch (IOException ex) {
                    Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            
        }

    }

    private boolean isValidCondition() {
        boolean registration = false;
        if (nullChecq() && passMatch()) {
            System.out.println("Condition valid");
            registration = true;
        } else {
            System.out.println("Condition Invalid");
            registration = false;
        }
        return registration;
    }

    private boolean nullChecq() {
        boolean nullChecq = false;
        if (tfUserName.getText().trim().isEmpty()
                || tfFullName.getText().trim().isEmpty()
                || pfUserPassword.getText().isEmpty()
                || pfReUserPassword.getText().isEmpty()) {

            System.out.println("Empty user Name");
            nullChecq = false;
        } else {
            System.out.println("User Name not Empty");
            nullChecq = true;
        }
        return nullChecq;
    }

    private boolean passMatch() {
        boolean passMatch = false;
        String password = pfUserPassword.getText();
        String rePass = pfReUserPassword.getText();

        if (password.matches(rePass)) {
            System.out.println("Password Match");
            passMatch = true;

        } else {
            System.out.println("Password Not Match");
            passMatch = false;
        }
        return passMatch;

    }

    @FXML
    private void pfKeyTyped(KeyEvent event) {
        if (pfUserPassword.getText().matches(pfReUserPassword.getText())) {
            System.out.println("Match");
        } else {
            System.out.println("Not Match");
        }
    }

}
