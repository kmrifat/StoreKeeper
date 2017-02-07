/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.sell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Getway.CustomerGetway;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import DAL.Customer;
import List.ListCustomer;
import controller.RegistrationController;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author rifat
 */
public class ViewCustomerController implements Initializable {

    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();

    @FXML
    private AnchorPane acCustomerMainContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListCustomer> tblCustomer;
    @FXML
    private TableColumn<Object, Object> tblClmName;
    @FXML
    private TableColumn<Object, Object> tblClmContNo;
    @FXML
    private TableColumn<Object, Object> tblClmAddres;
    @FXML
    private TableColumn<Object, Object> tblClmDate;
    @FXML
    private TableColumn<Object, Object> tblClmAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmTotalBuy;

    String userId;

    userNameMedia nameMedia;
    @FXML
    private Button btnRefresh;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void tfSearchOnKeyReleased(Event event) {
        SortedList<ListCustomer> sortList = new SortedList<>(customer.customerList);
        customer.customerName = tfSearch.getText().trim();
        tblCustomer.setItems(customer.customerList);
        tblClmName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmContNo.setCellValueFactory(new PropertyValueFactory<>("customerContNo"));
        tblClmAddres.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tblClmTotalBuy.setCellValueFactory(new PropertyValueFactory<>("totalBuy"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        tblClmAddBy.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        customerGetway.searchView(customer);
        
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        AddCustomerController acc = new AddCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/sell/AddCustomer.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCustomerController addCustomerController = fXMLLoader.getController();
            media.setId(userId);
            addCustomerController.setNameMedia(nameMedia);
            addCustomerController.lblCustomerContent.setText("ADD CUSTOMER");
            addCustomerController.btnUpdate.setVisible(false);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (tblCustomer.getSelectionModel().getSelectedItem() != null) {
            selectedView();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (tblCustomer.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("CONFIRM");
            alert.setContentText("Are You Sure to Delete this item??");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ListCustomer listCustomer = tblCustomer.getSelectionModel().getSelectedItem();
                String item = listCustomer.getId();
                customer.id = item;
                customerGetway.delete(customer);
                tblCustomer.getItems().clear();
                viewDetails();
            }
            
        } else {
            System.out.println("EMPTY SELECTION");
        }

    }

    public void viewDetails() {
        tblCustomer.setItems(customer.customerList);
        tblClmName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmContNo.setCellValueFactory(new PropertyValueFactory<>("customerContNo"));
        tblClmAddres.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tblClmTotalBuy.setCellValueFactory(new PropertyValueFactory<>("totalBuy"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        tblClmAddBy.setCellValueFactory(new PropertyValueFactory<>("addBy"));
        customerGetway.view(customer);
    }

    public void selectedView() {
        ListCustomer listCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        String item = listCustomer.getId();
        if (!item.isEmpty()) {
            AddCustomerController acc = new AddCustomerController();
            userNameMedia media = new userNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/view/application/sell/AddCustomer.fxml"));
            try {
                fXMLLoader.load();
                Parent parent = fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddCustomerController addCustomerController = fXMLLoader.getController();
                media.setId(userId);
                addCustomerController.setNameMedia(nameMedia);
                addCustomerController.lblCustomerContent.setText("Customer Details");
                addCustomerController.btnSave.setVisible(false);
                addCustomerController.customerId = listCustomer.getId();
                addCustomerController.viewDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
        if(event.getClickCount() == 2){
            selectedView();
        }else{
            System.out.println("CLICK");
        }
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tfSearch.clear();
        customer.customerList.clear();
        viewDetails();
    }
    
    
}
