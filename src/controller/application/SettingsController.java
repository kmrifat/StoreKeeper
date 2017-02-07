/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.application.settings.MyAccountController;
import controller.application.settings.OrgSettingController;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class SettingsController implements Initializable {
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miOrganize;
    @FXML
    private MenuItem miBackup;
    @FXML
    private StackPane spSettingContent;
    @FXML
    private Label lblCurrentStatus;
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    private String userID;
    
    userNameMedia usrMedia;
    
    
    @FXML
    public BorderPane bpSettings;
    
    

    public userNameMedia getUsrMedia() {
        return usrMedia;
    }

    public void setUsrMedia(userNameMedia usrMedia) {
        userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    public void miMyASccountOnClick(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("My Account");
        
        MyAccountController myAccount = new MyAccountController();
        userNameMedia usrIdMedia = new userNameMedia();
        
        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/view/application/settings/MyAccount.fxml").openStream());
        
        usrIdMedia.setId(userID);
        
        MyAccountController mAccount = fxmlload.getController();
        
        mAccount.setUsrMediaID(usrMedia);
        mAccount.showDetails();
        AnchorPane acPane = fxmlload.getRoot();
        
        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);
        
    }

    @FXML
    private void miOrganizeOnAction(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Org. Setup");
        
        OrgSettingController orgSetting = new OrgSettingController();
        userNameMedia useridMedia = new userNameMedia();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/settings/OrgSetting.fxml").openStream());
        
        useridMedia.setId(userID);
        
        OrgSettingController orgnizeUsrId = fXMLLoader.getController();
        orgnizeUsrId.setUsrIdMedia(useridMedia);
        orgnizeUsrId.showDetails();
        spSettingContent.getChildren().clear();
        AnchorPane orgAp = fXMLLoader.getRoot();
        spSettingContent.getChildren().add(orgAp);
    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {
        
    }
    
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where id=?");
            pst.setString(1, userID);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(18)==0){
                    miOrganize.setDisable(true);
                }else{
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
