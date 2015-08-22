/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import custom.CustomTf;
import custom.CustomPf;
import dataBase.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.Users;
import javafx.scene.image.Image;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class LoginController implements Initializable {

    Users users = new Users();

    @FXML
    private TextField tfUserName;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;

    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();
    
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink hlCrateAccount;

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cTF.clearTextFieldByButton(tfUserName, btnUserNameTfClear);
        cPF.clearPassFieldByButton(pfUserPassword, btnPassFieldClear);
        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(pfUserPassword.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);

    }

    @FXML
    private void hlCreateAnAccount(ActionEvent event) throws IOException {
        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("SELECT Id FROM User ORDER BY Id ASC LIMIT 1");
            rs = pst.executeQuery();
            while (rs.next()) {
                apMother.setOpacity(0.5);
                Action newActon = Dialogs.create().title("Sucess")
                        .actions(Dialog.ACTION_CLOSE)
                        .masthead("permission denied")
                        .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                        .message("This hyperlink only available only for first user.\nYou can not add or create an account by clicking this link.\nYou need to admin permission to create an account")
                        .showError();
                if (newActon == Dialog.ACTION_CLOSE) {
                    apMother.setOpacity(1);
                }
                return;
            }
            loadRegistration();

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {

        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        userNameMedia media = new userNameMedia();

        ApplicationController apController = new ApplicationController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Application.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);
//        adminPanelStage.setMinHeight(700.0);
//        adminPanelStage.setMinWidth(850.0);

        if (isValidCondition()) {
            try {
                pst = con.prepareStatement("select * from User where UsrName=? and Password=?");
                pst.setString(1, tfUserName.getText());
                pst.setString(2, pfUserPassword.getText());
                rs = pst.executeQuery();

                while (rs.next()) {
                    pst = con.prepareStatement("select * from User where UsrName=? and Status=?");
                    pst.setString(1, tfUserName.getText());
                    pst.setInt(2, 1);
                    rs = pst.executeQuery();

                    while (rs.next()) {
//                        System.out.println(rs.getInt(1));
                        userNameMedia usrNameMedia = new userNameMedia(rs.getString(1), rs.getString(2));
                        ApplicationController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.btnHomeOnClick(event);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.getIcons().add(new Image("/image/icon.png"));
                        adminPanelStage.setTitle(rs.getString(3));
                        adminPanelStage.show();

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        System.out.println("Now You Ready to go to Admin Panel");
                        return;
                    }
                    System.out.println("Account Not Active");
                    Action acNotActive = Dialogs.create()
                            .message("This account not active right now \n "
                                    + "Please contact with admin to active your account \n"
                                    + " Thank You")
                            .actions(Dialog.ACTION_CLOSE)
                            .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                            .showConfirm();
                    return;
                }
                System.out.println("Password Not Match");
                Action acNotActive = Dialogs.create()
                        .message("\n User Name and Password Not Match")
                        .actions(Dialog.ACTION_CLOSE)
                        .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                        .showError();

            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if (tfUserName.getText().trim().isEmpty()
                || pfUserPassword.getText().isEmpty()) {
            Action warn = Dialogs.create()
                    .actions(Dialog.ACTION_OK)
                    .message("Please Fill Text Field And Password Properly")
                    .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                    .showWarning();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadRegistration() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(true);
            nStage.setTitle("Registration -StoreKeeper");
            nStage.show();
            Stage stage = (Stage) hlCrateAccount.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
