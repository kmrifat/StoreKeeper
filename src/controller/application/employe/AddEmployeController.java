/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.employe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Getway.UsersGetway;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import media.userNameMedia;
import custom.*;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import DAL.Users;
import dataBase.DBProperties;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class AddEmployeController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextArea taAddress;
    @FXML
    private ImageView imvUsrImg;
    
    private File file;
    private BufferedImage bufferedImage;
    private Image image;
    
    private String imagePath;
    private String usrId;
    
    private userNameMedia nameMedia;
    @FXML
    public Button btnClrUsrName;
    @FXML
    public Button btnClrFullName;
    @FXML
    public Button btnClrEmail;
    @FXML
    public Button btnClrPhone;
    @FXML
    public Button btnClrSalary;
    @FXML
    public Button btnClrPassword;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();

    

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomTf cTf = new CustomTf();
        cTf.clearTextFieldByButton(tfUserName, btnClrUsrName);
        cTf.clearTextFieldByButton(tfFullName, btnClrFullName);
        cTf.clearTextFieldByButton(tfEmail, btnClrEmail);
        cTf.clearTextFieldByButton(tfPhone, btnClrPhone);
        cTf.clearTextFieldByButton(tfSalary, btnClrSalary);
        cTf.clearTextFieldByButton(tfPassword, btnClrPassword);

        cTf.numaricTextfield(tfSalary);
        
        BooleanBinding binding = tfUserName.textProperty().isEmpty()
                .or(tfFullName.textProperty().isEmpty()
                .or(tfPhone.textProperty().isEmpty())
                .or(tfPassword.textProperty().isEmpty()));
        btnSave.disableProperty().bind(binding);
    }    

    @FXML
    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
        );

        fileChooser.setTitle("Choise a Image File");

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println(file);
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imvUsrImg.setImage(image);
            imagePath = file.getAbsolutePath();
        }
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        users.userName = tfUserName.getText();
        users.fullName = tfFullName.getText();
        users.emailAddress = tfEmail.getText();
        users.contactNumber = tfPhone.getText();
        users.salary = tfSalary.getText();
        users.address = taAddress.getText();
        users.password = tfPassword.getText();
        users.imagePath = imagePath;
        users.creatorId = usrId;
        usersGetway.save(users);
        basicPermission();
    }

    
    private void basicPermission(){
        DBConnection dbCon = new DBConnection();
        Connection con;
        ResultSet rs;
        PreparedStatement pst;
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("Select Id FROM "+db+".User where UsrName=?");
            pst.setString(1, tfUserName.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into "+db+".UserPermission values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setInt(2, 0);
                pst.setInt(3, 0);
                pst.setInt(4, 0);
                pst.setInt(5, 0);
                pst.setInt(6, 0);
                pst.setInt(7, 0);
                pst.setInt(8, 0);
                pst.setInt(9, 0);
                pst.setInt(10, 0);
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setInt(14, 0);
                pst.setInt(15, 0);
                pst.setInt(16, 0);
                pst.setInt(17, 0);
                pst.setInt(18, 0);
                pst.setInt(19, 0);
                pst.setInt(20, rs.getInt("Id"));

                pst.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
