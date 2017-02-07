/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.employe;

import Getway.UsersGetway;
import dataBase.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import media.userNameMedia;
import custom.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;

import List.ListEmployee;

import DAL.Users;
import controller.RegistrationController;
import dataBase.DBProperties;
import java.util.Optional;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ViewEmployeController implements Initializable {

    CustomPf cPf = new CustomPf();
    CustomTf cTf = new CustomTf();
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    private File file;
    private BufferedImage bufferedImage;
    private String imagePath;
    private Image image;
    private Blob blobImage;

    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    private String userId;
    private String name;
    private String id;
    private userNameMedia nameMedia;
    private String creatorId;
    private String creatorName;

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfSearch;
    @FXML
    private Rectangle recUsrImage;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfDateofJoin;
    @FXML
    private TextField tfCreatedBy;
    @FXML
    private TextArea taAddress;
    @FXML
    public Button btnClrFulNametf;
    @FXML
    public Button btnClrEmailtf;
    @FXML
    public Button btnClrPhonetf;
    @FXML
    public Button btnClrSalarytf;
    @FXML
    public Button btnClrDatestf;
    @FXML
    public Button btnClrCreatortf;
    @FXML
    private CheckBox cbStatus;
    @FXML
    private Hyperlink hlChangePassword;
    @FXML
    private Hyperlink hlViewPermission;
    @FXML
    private TableView<ListEmployee> tblEmoyeeList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TableColumn<Object, Object> clmEmployeName;
    @FXML
    private Label lblCreator;

    Image usrImg = new Image("/image/rifat.jpg");

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        name = nameMedia.getUsrName();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        apMotherContent.getStylesheets().add(ViewEmployeController.class.getResource("/style/MainStyle.css").toExternalForm());
        cTf.clearTextFieldByButton(tfFullName, btnClrFulNametf);
        cTf.clearTextFieldByButton(tfEmailAddress, btnClrEmailtf);
        cTf.clearTextFieldByButton(tfPhoneNumber, btnClrPhonetf);
        cTf.clearTextFieldByButton(tfDateofJoin, btnClrDatestf);
        cTf.clearTextFieldByButton(tfCreatedBy, btnClrCreatortf);
        cTf.clearTextFieldByButton(tfSalary, btnClrSalarytf);

        cTf.numaricTextfield(tfSalary);

    }

    @FXML
    private void tfSearchOnAction(ActionEvent event) {

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
            recUsrImage.setFill(new ImagePattern(image));
            imagePath = file.getAbsolutePath();
        }

    }

    @FXML
    private void tblViewOnClick(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }

    public void tblEmloyeOnClick(Event event) {
        setselectedView();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws FileNotFoundException {

        users.userName = tfUserName.getText();
        users.fullName = tfFullName.getText();
        users.emailAddress = tfEmailAddress.getText();
        users.contactNumber = tfPhoneNumber.getText();
        users.salary = tfSalary.getText();
        users.address = taAddress.getText();
        users.image = usrImg;
        if (cbStatus.isSelected()) {
            users.status = "1";
        } else {
            users.status = "0";
        }
        users.imagePath = imagePath;
        users.creatorId = userId;
        usersGetway.update(users);
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.setHeaderText("Are You sure ?");
        alert.setContentText("Are you sure to remove this employee \n Click OK to confirm");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            usersGetway.selectedView(users);
            usersGetway.delete(users);
        }
        
        tblEmoyeeList.getItems().clear();
        showDetails();

    }

    @FXML
    private void cbOnAction(ActionEvent event) {
        if (cbStatus.isSelected()) {
            cbStatus.setText("Active");
        } else {
            cbStatus.setText("Deactive");
        }
    }

    @FXML
    private void hlChangePasswordOnAction(ActionEvent event) {

    }

    @FXML
    private void hlViewPermissionOnAction(ActionEvent event) throws IOException {
        usersGetway.selectedView(users);
        id = users.id;

        EmployeePermissionController pcc = new EmployeePermissionController();
        userNameMedia usrID = new userNameMedia();
        FXMLLoader loader = new FXMLLoader();
        System.out.println(id);
        loader.setLocation(getClass().getResource("/view/application/employe/EmployeePermission.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        EmployeePermissionController PermissionController = loader.getController();
        nameMedia.setId(id);
        PermissionController.setMedia(nameMedia);
        PermissionController.checqPermission();
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }

    @FXML
    private void hlViewUpdateHistory(ActionEvent event) throws IOException {
        String emp = "Employee";
        History history = new History();
        history.viewText(emp, tfUserName.getText(), name);
        System.out.println("view");
    }

    public void setselectedView() {
        clearAll();
        ListEmployee employeeList = tblEmoyeeList.getSelectionModel().getSelectedItem();
        if (employeeList != null) {
            users.id = employeeList.getEmployeeId();
            usersGetway.selectedView(users);
            id = users.id;
            tfUserName.setText(users.userName);
            tfFullName.setText(users.fullName);
            tfPhoneNumber.setText(users.contactNumber);
            tfEmailAddress.setText(users.emailAddress);
            tfSalary.setText(users.salary);
            tfDateofJoin.setText(users.date);
            creatorId = users.creatorId;
            taAddress.setText(users.address);
            image = users.image;
            recUsrImage.setFill(new ImagePattern(image));
            sql.creatorNameFindar(creatorId, lblCreator);
            tfCreatedBy.setText(lblCreator.getText());
            if (users.status.matches("1")) {
                cbStatus.setSelected(true);
                cbStatus.setText("Active");
            } else if (users.status.matches("0")) {
                cbStatus.setSelected(false);
                cbStatus.setText("Deactive");
            }
            if (users.id.matches("1")) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                hlChangePassword.setVisible(false);
                hlViewPermission.setVisible(false);
            } else {
                btnUpdate.setVisible(true);
                btnDelete.setVisible(true);
                hlChangePassword.setVisible(true);
                hlViewPermission.setVisible(true);
            }

        }
    }

    public void showDetails() {
        tblEmoyeeList.setItems(users.employeeLists);
        clmEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        clmEmployeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        usersGetway.view(users);

    }

    public void checqPermission() {
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where UserId=?");
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(13) != 1) {
                    hlChangePassword.setDisable(true);
                } else {
                    hlChangePassword.setDisable(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearAll() {
        tfUserName.clear();
        tfFullName.clear();
        tfCreatedBy.clear();
        tfSalary.clear();
        tfEmailAddress.clear();
        tfDateofJoin.clear();
        tfPhoneNumber.clear();
        taAddress.clear();
    }
}
