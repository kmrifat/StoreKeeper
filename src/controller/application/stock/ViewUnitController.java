/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import BLL.UnitBLL;
import Getway.UnitGetway;
import dataBase.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import List.ListUnit;
import media.userNameMedia;
import DAL.Unit;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ViewUnitController implements Initializable {

    Unit unit = new Unit();
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();

    private String usrId;
    private String creatorId;
    private String unitId;

    private userNameMedia media;

    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ListUnit> tblViewUnit;
    @FXML
    private TableColumn<Object, Object> clmUnitId;
    @FXML
    private TableColumn<Object, Object> clmUnitName;
    @FXML
    private TableColumn<Object, Object> clmUnitDescription;
    @FXML
    private TableColumn<Object, Object> clmUnitCreator;
    @FXML
    private TableColumn<Object, Object> clmUnitCreateDate;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void tblViewUnitOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewDetails();
        } else {
            System.out.println(event.getClickCount());
        }
    }

    @FXML
    private void btnAddItemOnAction(ActionEvent event) {

        AddUnitController addUnitController = new AddUnitController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddUnit.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddUnitController unitController = fxmlLoader.getController();
            media.setId(usrId);
            unitController.setNameMedia(media);
            unitController.lblContent.setText("ADD UNIT");
            unitController.btnUpdate.setVisible(false);
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
        if (tblViewUnit.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!tblViewUnit.getSelectionModel().isEmpty()) {
            ListUnit selectedUnit = tblViewUnit.getSelectionModel().getSelectedItem();
            String unitName = selectedUnit.getUnitName();
            unitId = selectedUnit.getUnitId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                unit.id = unitId;
                unitBLL.delete(unit);
                tfSearchOnKeyResele(event);
            } else {

            }
            
        } else {
            System.out.println("NULL SELECTED");
        }

    }

    @FXML
    private void miSearchOnAction(ActionEvent event) {
        tfSearch.requestFocus();
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddItemOnAction(event);
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        miUpdateOnAction(event);
    }

    public void showDetails() {
        tblViewUnit.setItems(unit.unitDetails);
        clmUnitId.setCellValueFactory(new PropertyValueFactory<>("unitId"));
        clmUnitName.setCellValueFactory(new PropertyValueFactory<>("unitName"));
        clmUnitDescription.setCellValueFactory(new PropertyValueFactory<>("unitDescription"));
        clmUnitCreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmUnitCreateDate.setCellValueFactory(new PropertyValueFactory<>("dateOfCreation"));
        unitGetway.view(unit);
    }

    private void viewDetails() {
        if (!tblViewUnit.getSelectionModel().isEmpty()) {
            ListUnit selectedUnit = tblViewUnit.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedUnit.getUnitId());
            String items = selectedUnit.getUnitId();
            if (!items.equals(null)) {
                AddUnitController addUnitController = new AddUnitController();
                userNameMedia media = new userNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddUnit.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddUnitController unitController = fxmlLoader.getController();
                    media.setId(usrId);
                    unitController.setNameMedia(media);
                    unitController.lblContent.setText("UNIT DETAILS");
                    unitController.btnUpdate.setVisible(true);
                    unitController.btnSave.setVisible(true);
                    unitController.unitId = selectedUnit.getUnitId();
                    unitController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("empty Selection");
        }

    }

    @FXML
    public void tfSearchOnKeyResele(Event event) {
        unit.unitDetails.clear();
        unit.unitName = tfSearch.getText().trim();
        tblViewUnit.setItems(unit.unitDetails);
        clmUnitId.setCellValueFactory(new PropertyValueFactory<>("unitId"));
        clmUnitName.setCellValueFactory(new PropertyValueFactory<>("unitName"));
        clmUnitDescription.setCellValueFactory(new PropertyValueFactory<>("unitDescription"));
        clmUnitCreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmUnitCreateDate.setCellValueFactory(new PropertyValueFactory<>("dateOfCreation"));
        unitGetway.searchView(unit);

    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        unit.unitDetails.clear();
        showDetails();
    }
}
