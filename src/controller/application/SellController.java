/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.application.sell.ViewCustomerController;
import controller.application.sell.ViewSellController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class SellController implements Initializable {

    @FXML
    private ToggleButton tbtnSell;
    @FXML
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnReports;
    @FXML
    private Label lblPathInfo;
    @FXML
    private StackPane spMainContent;

    userNameMedia nameMedia;

    String userId;
    @FXML
    public AnchorPane acMainSells;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        tbtnSell.setSelected(true);
        tbtnCustomer.setToggleGroup(group);
        tbtnSell.setToggleGroup(group);
        tbtnReports.setToggleGroup(group);
    }

    @FXML
    public void tbtnSellOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Sells");
        ViewSellController sellController = new ViewSellController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewSell.fxml").openStream());
        media.setId(userId);
        ViewSellController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
//        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Customers");
        ViewCustomerController customerController = new ViewCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewCustomer.fxml").openStream());
        media.setId(userId);
        ViewCustomerController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event) throws IOException {

    }

}
