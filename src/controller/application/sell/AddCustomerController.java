package controller.application.sell;

import BLL.CustomerBLL;
import Getway.CustomerGetway;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.Customer;

/**
 * Created by rifat on 8/12/15.
 */
public class AddCustomerController implements Initializable {
    @FXML
    private TextField tfCustomerName;
    @FXML
    private TextArea taCustomerContact;
    @FXML
    private TextArea taCustomerAddress;
    @FXML
    public Button btnSave;
    @FXML
    public Label lblCustomerContent;
    @FXML
    private Button btnClose;
    @FXML
    public Button btnUpdate;
    
    public String customerId;
    
    private String userId;
    
    userNameMedia nameMedia;
    

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    CustomerBLL customerBLL = new CustomerBLL();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        customer.customerName = tfCustomerName.getText().trim();
        customer.customerAddress = taCustomerAddress.getText().trim();
        customer.customerConNo = taCustomerContact.getText().trim();
        customer.userId = userId;
        customerBLL.save(customer);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        customer.id = customerId;
        customer.customerName = tfCustomerName.getText().trim();
        customer.customerAddress = taCustomerAddress.getText().trim();
        customer.customerConNo = taCustomerContact.getText().trim();
        customerBLL.update(customer);
    }
    
    public void viewDetails(){
        customer.id = customerId;
        customerGetway.selectedView(customer);
        tfCustomerName.setText(customer.customerName);
        taCustomerContact.setText(customer.customerConNo);
        taCustomerAddress.setText(customer.customerAddress);
    }
}
