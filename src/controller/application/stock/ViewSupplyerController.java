/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import BLL.SupplyerBLL;
import Getway.SupplyerGetway;
import custom.History;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import List.ListSupplyer;
import DAL.Supplyer;
import java.util.Optional;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ViewSupplyerController implements Initializable {
    @FXML
    public AnchorPane acContent;
    SQL sql = new SQL();
    Supplyer supplyer = new Supplyer();
    SupplyerGetway supplyerGetway = new SupplyerGetway();
    SupplyerBLL supplyerBLL = new SupplyerBLL();
    History history = new History();

    private String usrId;
    private String creatorName;
    private String creatorId;
    private String supplyerId;
    private String userName;

    private userNameMedia media;


    @FXML
    private TableView<ListSupplyer> tblSupplyer;
    @FXML
    private TableColumn<Object, Object> clmSUpplyerId;
    @FXML
    private TableColumn<Object, Object> clmSupplyerName;
    @FXML
    private TableColumn<Object, Object> clmSupplyerPhoneNumber;
    @FXML
    private TableColumn<Object, Object> clmSupplyerAddress;
    @FXML
    private TableColumn<Object, Object> clmSupplyerJoining;
    @FXML
    private TableColumn<Object, Object> clmSupplyerDescription;

    private final static int dataSize = 10_023;
    private final static int rowsPerPage = 1000;
    @FXML
    private Button btnAdditems;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfSearch;
    private Text text;
    @FXML
    private MenuItem mbSearch;
    @FXML
    private Button btnRefresh;


    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void tblSupplyerOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }

    }

    @FXML
    private void tblSupplyerOnKeyPress(KeyEvent event) {

    }


    @FXML
    public void tfSearchOnType(Event event) {
        supplyer.supplyerDetails.removeAll();
        supplyer.supplyerName = tfSearch.getText().trim();

        tblSupplyer.setItems(supplyer.supplyerDetails);
        clmSUpplyerId.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmSupplyerName.setCellValueFactory(new PropertyValueFactory<>("supplyerName"));
        clmSupplyerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("supplyerPhoneNumber"));
        clmSupplyerAddress.setCellValueFactory(new PropertyValueFactory<>("supplyerAddress"));
        clmSupplyerDescription.setCellValueFactory(new PropertyValueFactory<>("supplyerDescription"));
        clmSupplyerJoining.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        supplyerGetway.searchView(supplyer);
    }


    public void showDetails() {
        tblSupplyer.setItems(supplyer.supplyerDetails);
        clmSUpplyerId.setCellValueFactory(new PropertyValueFactory<>("supplyerId"));
        clmSupplyerName.setCellValueFactory(new PropertyValueFactory<>("supplyerName"));
        clmSupplyerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("supplyerPhoneNumber"));
        clmSupplyerAddress.setCellValueFactory(new PropertyValueFactory<>("supplyerAddress"));
        clmSupplyerDescription.setCellValueFactory(new PropertyValueFactory<>("supplyerDescription"));
        clmSupplyerJoining.setCellValueFactory(new PropertyValueFactory<>("dataOfjoining"));
        supplyerGetway.view(supplyer);

    }





    @FXML
    private void btnAdditemsOnAction(ActionEvent event) {
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
            media.setId(usrId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Item");
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
        tfSearchOnType(event);


    }

    @FXML
    private void btnUpdateOnAction(Event event) {
        if(tblSupplyer.getSelectionModel().getSelectedItem() != null){
            viewDetails();
        }else {
            System.out.println("EMPTY SELECTION");
        }

    }

    private void viewDetails() {
        if(!tblSupplyer.getSelectionModel().isEmpty()){
            ListSupplyer selectedSupplyer = tblSupplyer.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedSupplyer.getSupplyerId());
            String items = selectedSupplyer.getSupplyerId();
            if (!items.equals(null)) {
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
                    media.setId(usrId);
                    supplyerController.setMedia(media);
                    supplyerController.lblCaption.setText("Supplyer Details");
                    supplyerController.btnUpdate.setVisible(true);
                    supplyerController.btnSave.setVisible(false);
                    supplyerController.supplyerId = selectedSupplyer.getSupplyerId();
                    supplyerController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("empty Selection");
        }



    }

    @FXML
    private void mbView(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void mbViewHistory(ActionEvent event) {
    }

    @FXML
    private void mbAddNew(ActionEvent event) {
        btnAdditemsOnAction(event);
    }

    @FXML
    private void mbDeleteItem(ActionEvent event) {
        System.out.println("clicked to delete");
        acContent.setOpacity(0.5);
        ListSupplyer selectedSupplyer = tblSupplyer.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            supplyer.id = selectedSupplyer.getSupplyerId();
            System.out.println(supplyer.id+ "On hear");
            supplyerBLL.delete(supplyer);
            tblSupplyer.getItems().clear();
            showDetails();
        }else{
            
        }
        


    }

    @FXML
    private void mbEdit(ActionEvent event) {
        btnUpdateOnAction(event);
        tfSearchOnType(event);
    }

    @FXML
    private void mbSearch(ActionEvent event) {
        tblSupplyer.getSelectionModel().clearSelection();
        tfSearch.requestFocus();

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        mbDeleteItem(event);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        supplyer.supplyerDetails.clear();
        showDetails();
    }


}
