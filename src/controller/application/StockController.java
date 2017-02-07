/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.ApplicationController;
import controller.application.stock.CurrentStoreController;
import controller.application.stock.ViewBrandController;
import controller.application.stock.ViewCatagoryController;
import controller.application.stock.ViewRMAController;
import controller.application.stock.ViewSupplyerController;
import controller.application.stock.ViewUnitController;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class StockController implements Initializable {
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private StackPane spMainContent;

    private String usrId;

    private userNameMedia userId;
    @FXML
    public BorderPane bpStore;
    @FXML
    private Label lblHeader;
    @FXML
    private ToggleButton btnStock;
    @FXML
    private ToggleButton btnSupplyer;
    @FXML
    private ToggleButton btnBrands;
    @FXML
    private ToggleButton btnCatagory;
    @FXML
    private ToggleButton btnUnit;
    @FXML
    private ToggleButton btnRma;
    @FXML
    private ToggleButton btnRepoerts;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public userNameMedia getUserId() {
        return userId;
    }

    public void setUserId(userNameMedia userId) {
        usrId = userId.getId();
        this.userId = userId;
    }
    
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ToggleGroup toggleGroup = new ToggleGroup();
        btnStock.setSelected(true);
        btnStock.setToggleGroup(toggleGroup);
        btnSupplyer.setToggleGroup(toggleGroup);
        btnBrands.setToggleGroup(toggleGroup);
        btnCatagory.setToggleGroup(toggleGroup);
        btnUnit.setToggleGroup(toggleGroup);
        btnRma.setToggleGroup(toggleGroup);
        btnRepoerts.setToggleGroup(toggleGroup);


    }


    @FXML
    public void btnStockOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Store");
        CurrentStoreController asc = new CurrentStoreController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/CurrentStore.fxml").openStream());
        media.setId(usrId);
        CurrentStoreController currentStoreController = fXMLLoader.getController();
        currentStoreController.setMedia(userId);
        currentStoreController.viewDetails();
        currentStoreController.apCombobox.getStylesheets().add("/style/StoreCombobox.css");
        currentStoreController.settingPermission();
        StackPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSupplyerOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Supplyer");
        ViewSupplyerController vsc = new ViewSupplyerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewSupplier.fxml").openStream());
        media.setId(usrId);
        ViewSupplyerController viewSupplyerController = fXMLLoader.getController();
        viewSupplyerController.setMedia(userId);
        viewSupplyerController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnBrandsOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Brands");
        ViewBrandController vbc = new ViewBrandController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewBrand.fxml").openStream());
        media.setId(usrId);
        ViewBrandController viewBrandController = fXMLLoader.getController();
        viewBrandController.setMedia(userId);
        viewBrandController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCatagoryOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Catagories");
        ViewCatagoryController vcc = new ViewCatagoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewCategory.fxml").openStream());
        media.setId(usrId);
        ViewCatagoryController viewCatagoryController = fXMLLoader.getController();
        viewCatagoryController.setMedia(userId);
        viewCatagoryController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnUnitOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Unit");
        ViewUnitController vuc = new ViewUnitController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewUnit.fxml").openStream());
        media.setId(usrId);
        ViewUnitController viewUnitController = fXMLLoader.getController();
        viewUnitController.setMedia(userId);
        viewUnitController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRmaOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("RMA");
        ViewRMAController vrmac = new ViewRMAController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewRMA.fxml").openStream());
        media.setId(usrId);
        ViewRMAController viewRMAController = fXMLLoader.getController();
        viewRMAController.setMedia(userId);
        viewRMAController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }
    
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where id=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(2)==0 && rs.getInt(9) == 0){
                    btnSupplyer.setDisable(true);
                }if(rs.getInt(4) == 0 && rs.getInt(10) == 0){
                    btnBrands.setDisable(true);
                }if(rs.getInt(5) == 0 && rs.getInt(11) == 0){
                    btnCatagory.setDisable(true);
                }if(rs.getInt(6) == 0 && rs.getInt(12) == 0){
                    btnUnit.setDisable(true);
                }if(rs.getInt(14) == 0){
                    btnRma.setDisable(true);
                }
                
                else{
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
