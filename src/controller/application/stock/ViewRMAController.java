/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import BLL.RmaBLL;
import Getway.RmaGetway;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import List.ListRma;
import media.userNameMedia;
import DAL.RMA;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ViewRMAController implements Initializable {

    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();

    private String usrId;
    private String rmaId;
    private userNameMedia media;
    private String creatorId;
    @FXML
    private TableView<ListRma> tblViewRMA;
    @FXML
    private TableColumn<Object, Object> clmRMAId;
    @FXML
    private TableColumn<Object, Object> clmRMAName;
    @FXML
    private Button btnAddNew;
    @FXML
    private TableColumn<Object, Object> clmRMADayes;
    @FXML
    private TableColumn<Object, Object> clmRMADiscription;
    @FXML
    private TableColumn<Object, Object> clmRMACreator;
    @FXML
    private TableColumn<Object, Object> clmRMADate;
    @FXML
    private Button btnRefresh;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    @FXML
    private TextField tfSearch;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void tblViewRMAOnClick(MouseEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            if (event.getClickCount() == 2) {
                viewDetails();
            }
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRMA = tblViewRMA.getSelectionModel().getSelectedItem();
            String rmaName = selectedRMA.getRmaName();
            rmaId = selectedRMA.getRamId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                rma.id = rmaId;
                rmaBLL.delete(rma);
                tfSearchOnKeyRelesh(event);
            }

        } else {
            System.out.println("NULL SELECTED");
        }
    }

    public void showDetails() {
        tblViewRMA.setItems(rma.rmaDetails);
        clmRMAId.setCellValueFactory(new PropertyValueFactory<>("ramId"));
        clmRMAName.setCellValueFactory(new PropertyValueFactory<>("rmaName"));
        clmRMADayes.setCellValueFactory(new PropertyValueFactory<>("rmaDays"));
        clmRMADiscription.setCellValueFactory(new PropertyValueFactory<>("rmaComment"));
        clmRMACreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmRMADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        rmaGetway.view(rma);
    }

    @FXML
    private void tblViewRMAOnKeyResele(KeyEvent event) {

    }

    @FXML
    public void btnAddNew(ActionEvent actionEvent) {
        AddRMAController addRMAController = new AddRMAController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddRMAController rmaController = fxmlLoader.getController();
            media.setId(usrId);
            rmaController.setMedia(media);
            rmaController.lblContent.setText("ADD RMA");
            rmaController.btnUpdate.setVisible(false);
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
    public void tfSearchOnKeyRelesh(Event event) {
        rma.rmaDetails.clear();
        rma.rmaName = tfSearch.getText().trim();
        tblViewRMA.setItems(rma.rmaDetails);
        clmRMAId.setCellValueFactory(new PropertyValueFactory<>("ramId"));
        clmRMAName.setCellValueFactory(new PropertyValueFactory<>("rmaName"));
        clmRMADayes.setCellValueFactory(new PropertyValueFactory<>("rmaDays"));
        clmRMADiscription.setCellValueFactory(new PropertyValueFactory<>("rmaComment"));
        clmRMACreator.setCellValueFactory(new PropertyValueFactory<>("creatorName"));
        clmRMADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        rmaGetway.searchView(rma);

    }

    private void viewDetails() {
        if (!tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRma = tblViewRMA.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedRma.getRamId());
            String items = selectedRma.getRamId();
            if (!items.equals(null)) {
                AddUnitController addUnitController = new AddUnitController();
                userNameMedia media = new userNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    AddRMAController rmaController = fxmlLoader.getController();
                    media.setId(usrId);
                    rmaController.setMedia(media);
                    rmaController.lblContent.setText("RMA DETAILS");
                    rmaController.btnUpdate.setVisible(true);
                    rmaController.btnSave.setVisible(true);
                    rmaController.rmaId = selectedRma.getRamId();
                    rmaController.showDetails();
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
    private void btnRefreshOnAction(ActionEvent event) {
        rma.rmaDetails.clear();
        showDetails();
    }

}
