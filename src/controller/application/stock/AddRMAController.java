/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.stock;

import BLL.RmaBLL;
import Getway.RmaGetway;
import custom.CustomTf;
import dataBase.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import media.userNameMedia;
import DAL.RMA;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class AddRMAController implements Initializable {

    @FXML
    public Label lblContent;

    CustomTf ctf = new CustomTf();
    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    private String usrId;

    public String rmaId;

    private userNameMedia media;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    private Button btnClose;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    @FXML
    private TextField tfRMAName;
    @FXML
    private TextField tfRMADayes;
    @FXML
    private TextArea taRMAComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding binding = tfRMAName.textProperty().isEmpty()
                .or(tfRMADayes.textProperty().isEmpty());
        btnSave.disableProperty().bind(binding);

        ctf.numaricTextfield(tfRMADayes);
    }

    @FXML
    private void btnSave(ActionEvent event) {
        rma.id = rmaId;
        rma.rmaName = tfRMAName.getText().trim();
        rma.rmaDays = tfRMADayes.getText().trim();
        rma.rmaComment = taRMAComment.getText();
        rma.creatorId = usrId;
        rmaBLL.save(rma);
    }
    
    



    @FXML
    private void tfRMADays(KeyEvent event) {
        
        if(tfRMADayes.getText().matches("[0-9]*")){
            
        }else{
            tfRMADayes.clear();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        rma.id = rmaId;
        rma.rmaName = tfRMAName.getText().trim();
        rma.rmaDays = tfRMADayes.getText().trim();
        rma.rmaComment = taRMAComment.getText();
        rmaBLL.update(rma);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void showDetails() {
        rma.id = rmaId;
        rmaGetway.selectedView(rma);
        tfRMAName.setText(rma.rmaName);
        tfRMADayes.setText(rma.rmaDays);
        taRMAComment.setText(rma.rmaComment);
    }
}

