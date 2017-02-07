/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import BLL.CatagoryBLL;
import dataBase.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import DAL.Catagory;
import Getway.CatagoryGetway;
import dataBase.DBProperties;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class AddCatagoryController implements Initializable {

    private String userId;
    private String brandId;
    private String brnadName;
    public String supplyerId;
    public String supplyerName;
    public String catagoryId;

    Catagory catagory = new Catagory();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    SQL sql = new SQL();
    
     DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    private userNameMedia media;
    @FXML
    private ComboBox<String> cbBrandName;
    @FXML
    private TextField tfCatagoryName;
    @FXML
    private TextArea taCatagoryDescription;
    @FXML
    public Button btnSave;
    @FXML
    private ComboBox<String> cbSupplyerName;
    @FXML
    private Button btnAddSupplyer;
    @FXML
    private Button btnAddBrand;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeaderContent;
    @FXML
    public Button btnClose;

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        userId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnSaveCatagory(ActionEvent event) {
        if (isNotNull()) {
            catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagory.creatorId = userId;
            catagoryBLL.save(catagory);

        }
    }

    @FXML
    private void btnAddSupplyerOnAction(ActionEvent event) {
        AddSupplyerController addSupplyerController = new AddSupplyerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddSupplyerController supplyerController = fxmlLoader.getController();
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Supplyer");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            supplyerController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        AddBrandController addSupplyerController = new AddBrandController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBrand.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddBrandController supplyerController = fxmlLoader.getController();
            media.setId(userId);
            supplyerController.setMedia(media);
            supplyerController.lblHeader.setText("Add Brand");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println("Clicked");
        if (isNotNull()) {
            catagory.id = catagoryId;
            if (!cbBrandName.getSelectionModel().isEmpty()) {
                catagory.brandName = cbBrandName.getSelectionModel().getSelectedItem();
            } else if (!cbBrandName.getPromptText().isEmpty()) {
                catagory.brandName = cbBrandName.getPromptText();
            }
            if (!cbSupplyerName.getSelectionModel().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
            } else if (!cbSupplyerName.getPromptText().isEmpty()) {
                catagory.supplyerName = cbSupplyerName.getPromptText();
            }
            catagory.catagoryName = tfCatagoryName.getText().trim();
            catagory.catagoryDescription = taCatagoryDescription.getText().trim();
            catagoryBLL.update(catagory);
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean isNotNull() {
        boolean isNotNull;
        if (tfCatagoryName.getText().trim().isEmpty()
                || cbSupplyerName.getSelectionModel().isEmpty()
                && cbSupplyerName.getPromptText().isEmpty()
                || cbBrandName.getSelectionModel().isEmpty()
                && cbBrandName.getPromptText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("Error : null found ");
            alert.setContentText("Please full all requre field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            

            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    @FXML
    private void cbSupplyerNameOnClick(MouseEvent event) {
        cbBrandName.getItems().clear();
        cbBrandName.setPromptText(null);
        try {
            pst = con.prepareStatement("select * from "+db+".Supplyer");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyerName = rs.getString(2);
                cbSupplyerName.getItems().remove(supplyerName);
                cbSupplyerName.getItems().add(supplyerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbBrandNameOnClick(MouseEvent event) throws SQLException {
        cbBrandName.getItems().clear();
        supplyerName = cbSupplyerName.getSelectionModel().getSelectedItem();
        supplyerId = sql.getIdNo(supplyerName, supplyerId, "Supplyer", "SupplyerName");

        pst = con.prepareStatement("select * from "+db+".Brands where SupplyerId=?");
        pst.setString(1, supplyerId);
        rs = pst.executeQuery();
        while (rs.next()) {
            cbBrandName.getItems().add(rs.getString(2));
        }
    }

    public void showDetails() {
        catagory.id = catagoryId;
        catagoryGetway.selectedView(catagory);
        tfCatagoryName.setText(catagory.catagoryName);
        taCatagoryDescription.setText(catagory.catagoryDescription);
        cbBrandName.setPromptText(catagory.brandName);
        cbSupplyerName.setPromptText(catagory.supplyerName);
    }
}
