/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.application.employe.AddEmployeController;
import controller.application.employe.ViewEmployeController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class EmployeController implements Initializable {
    @FXML
    private MenuItem btnViewEmployee;
    @FXML
    private MenuItem btnAddEmployee;
    
    private String userId;
    
    private userNameMedia nameMedia;
    @FXML
    private StackPane spEmployeContent;
    @FXML
    public BorderPane bpContent;
    @FXML
    private Label lblView;
    @FXML
    private ImageView ivEmployeIcon;
    
    Image image = new Image("/icon/d.png");

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivEmployeIcon.setImage(image);
    }    

    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        lblView.setText("Employee");
        ViewEmployeController vec = new ViewEmployeController();
        userNameMedia media = new userNameMedia();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/employe/ViewEmploye.fxml").openStream());
        media.setId(userId);
        
        ViewEmployeController viewEmployeController = fXMLLoader.getController();
        viewEmployeController.setNameMedia(nameMedia);
        viewEmployeController.showDetails();
        viewEmployeController.btnClrCreatortf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrEmailtf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrFulNametf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrSalarytf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrPhonetf.getStylesheets().add("/style/btnOnText.css");
//        viewEmployeController.checqPermission();
        
        AnchorPane acPane = fXMLLoader.getRoot();
        
        spEmployeContent.getChildren().clear();
        
        spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        lblView.setText("Add Employee");
        AddEmployeController vec = new AddEmployeController();
        userNameMedia media = new userNameMedia();
        
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/employe/AddEmploye.fxml").openStream());
        media.setId(userId);
        
        AddEmployeController addEmployeController = fXMLLoader.getController();
        addEmployeController.setNameMedia(nameMedia);
        addEmployeController.btnClrEmail.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrFullName.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrPassword.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrPhone.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrSalary.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrUsrName.getStylesheets().add("/style/btnOnText.css");
//        addEmployeController.showDetails();
        
        AnchorPane acPane = fXMLLoader.getRoot();
        
        spEmployeContent.getChildren().clear();
        
        spEmployeContent.getChildren().add(acPane);
        
    }

    
}
