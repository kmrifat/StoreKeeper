/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.employe;

import dataBase.DBConnection;
import dataBase.DBProperties;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class EmployeePermissionController implements Initializable {

    @FXML
    private CheckBox cbStockManegement;
    @FXML
    private CheckBox cbAddProduct;
    @FXML
    private CheckBox cbAddSupplyer;
    @FXML
    private CheckBox cbAddBrand;
    @FXML
    private CheckBox cbAddCatagory;
    @FXML
    private CheckBox cbUpdateProduct;
    @FXML
    private CheckBox cbUpdateSupplyert;
    @FXML
    private CheckBox cbUpdateBrands;
    @FXML
    private CheckBox cbUpdateCatagory;
    @FXML
    private CheckBox cbSellManegement;
    @FXML
    private CheckBox cbSellProduct;
    @FXML
    private CheckBox cbAddCustomer;
    @FXML
    private CheckBox cbUpdateCustomer;
    @FXML
    private CheckBox cbEmployeeManegement;
    @FXML
    private CheckBox cbAddEmployee;
    @FXML
    private CheckBox cbUpdateEmployee;
    @FXML
    private CheckBox cbEmployePassChange;
    @FXML
    private CheckBox cbUser;
    @FXML
    private CheckBox cbChangePassword;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClose;

    private String id;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    private userNameMedia media;
    @FXML
    private CheckBox cbAddUnit;
    @FXML
    private CheckBox cbUpdateUnit;
    @FXML
    private CheckBox cbProvideDiscount;
    @FXML
    private CheckBox cbOrgManage;
    @FXML
    private CheckBox cbMenageRMA;
    
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        id = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
//        int x = (args.length > 0) ? 1 : 2;

        int addProduct = (cbAddProduct.isSelected()) ? 1 : 0;
        int addSupplyer = (cbAddSupplyer.isSelected()) ? 1 : 0;
        int addBrand = (cbAddBrand.isSelected()) ? 1 : 0;
        int AddCatagory = (cbAddCatagory.isSelected()) ? 1 : 0;
        int AddUnit = (cbAddUnit.isSelected()) ? 1 : 0;
        int AddCustomer = (cbAddCustomer.isSelected()) ? 1 : 0;
        int UpdateProduct = (cbUpdateProduct.isSelected()) ? 1 : 0;
        int UpdateSupplyer = (cbUpdateSupplyert.isSelected()) ? 1 : 0;
        int UpdateBrand = (cbUpdateBrands.isSelected()) ? 1 : 0;
        int UpdateCatagory = (cbUpdateCatagory.isSelected()) ? 1 : 0;
        int UpdateUnit = (cbUpdateUnit.isSelected()) ? 1 : 0;
        int UpdateCustomer = (cbUpdateCustomer.isSelected()) ? 1 : 0;
        int SellProduct = (cbSellProduct.isSelected()) ? 1 : 0;
        int ProvideDiscount = (cbProvideDiscount.isSelected()) ? 1 : 0;
        int EmployeManage = (cbEmployeeManegement.isSelected()) ? 1 : 0;
        int OrgManage = (cbOrgManage.isSelected()) ? 1 : 0;
        int ChangeOwnPass = (cbChangePassword.isSelected()) ? 1 : 0;
        int menageRMA = (cbMenageRMA.isSelected()) ? 1 : 0;

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE "+db+".UserPermission SET AddProduct=?,AddSupplyer=?,AddBrand=?,AddCatagory=?,AddUnit=?,AddCustomer=?,UpdateProduct=?,UpdateSupplyer=?,UpdateBrand=?,UpdateCatagory=?,UpdateUnit=?,UpdateCustomer=?,SellProduct=?,ProvideDiscount=?,EmployeManage=?,OrgManage=?,ChangeOwnPass=?,RMAManage=? WHERE UserId=?");
//            pst.setString(1, id);
            pst.setInt(1, addProduct);
            pst.setInt(2, addSupplyer);
            pst.setInt(3, addBrand);
            pst.setInt(4, AddCatagory);
            pst.setInt(5, AddUnit);
            pst.setInt(6, AddCustomer);
            pst.setInt(7, UpdateProduct);
            pst.setInt(8, UpdateSupplyer);
            pst.setInt(9, UpdateBrand);
            pst.setInt(10, UpdateCatagory);
            pst.setInt(11, UpdateUnit);
            pst.setInt(12, UpdateCustomer);
            pst.setInt(13, SellProduct);
            pst.setInt(14, ProvideDiscount);
            pst.setInt(15, EmployeManage);
            pst.setInt(16, OrgManage);
            pst.setInt(17, ChangeOwnPass);
            pst.setInt(18, menageRMA);
            pst.setString(19, id);

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess");
            alert.setContentText("Permission change successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnCloseOnClick(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cbStockOnAction(ActionEvent event) {
        stockManeCbOperation();

    }

    @FXML
    private void cbSellManegementOnAction(ActionEvent event) {

    }

    @FXML
    private void cbEmployeeManegementOnAction(ActionEvent event) {
        if (cbEmployeeManegement.isSelected()) {
            cbAddEmployee.setSelected(true);
            cbEmployePassChange.setSelected(true);
            cbUpdateEmployee.setSelected(true);
        } else if (!cbEmployeeManegement.isSelected()) {
            cbAddEmployee.setSelected(true);
            cbEmployePassChange.setSelected(true);
            cbUpdateEmployee.setSelected(true);
        }
    }

    @FXML
    private void cbUserOnAction(ActionEvent event) {
    }

    public void checqPermission() {

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(2) == 1) {
                    cbAddProduct.setSelected(true);
                } else {
                    cbAddProduct.setSelected(false);
                }
                if (rs.getInt(3) == 1) {
                    cbAddSupplyer.setSelected(true);
                } else {
                    cbAddSupplyer.setSelected(false);
                }
                if (rs.getInt(4) == 1) {
                    cbAddBrand.setSelected(true);
                } else {
                    cbAddBrand.setSelected(false);
                }
                if (rs.getInt(5) == 1) {
                    cbAddCatagory.setSelected(true);
                } else {
                    cbAddCatagory.setSelected(false);
                }
                if (rs.getInt(6) == 1) {
                    cbAddUnit.setSelected(true);
                } else {
                    cbAddUnit.setSelected(false);
                }
                if (rs.getInt(7) == 1) {
                    cbAddCustomer.setSelected(true);
                } else {
                    cbAddCustomer.setSelected(false);
                }
                if (rs.getInt(8) == 1) {
                    cbUpdateProduct.setSelected(true);
                } else {
                    cbUpdateProduct.setSelected(false);
                }
                if (rs.getInt(9) == 1) {
                    cbUpdateSupplyert.setSelected(true);
                } else {
                    cbUpdateSupplyert.setSelected(false);
                }
                if (rs.getInt(10) == 1) {
                    cbUpdateBrands.setSelected(true);
                } else {
                    cbUpdateBrands.setSelected(false);
                }
                if (rs.getInt(11) == 1) {
                    cbUpdateCatagory.setSelected(true);
                } else {
                    cbUpdateCatagory.setSelected(false);
                }
                if (rs.getInt(12) == 1) {
                    cbUpdateUnit.setSelected(true);
                } else {
                    cbUpdateUnit.setSelected(false);
                }
                if (rs.getInt(13) == 1) {
                    cbUpdateCustomer.setSelected(true);
                } else {
                    cbUpdateCustomer.setSelected(false);
                }
                if (rs.getInt(14) == 1) {
                    cbMenageRMA.setSelected(true);
                } else {
                    cbMenageRMA.setSelected(false);
                }

                if (rs.getInt(15) == 1) {
                    cbSellProduct.setSelected(true);
                } else {
                    cbSellProduct.setSelected(false);
                }
                if (rs.getInt(16) == 1) {
                    cbProvideDiscount.setSelected(true);
                } else {
                    cbProvideDiscount.setSelected(false);
                }
                if (rs.getInt(17) == 1) {
                    cbEmployeeManegement.setSelected(true);
                    cbAddEmployee.setSelected(true);
                    cbEmployePassChange.setSelected(true);
                    cbUpdateEmployee.setSelected(true);
                } else {
                    cbEmployeeManegement.setSelected(false);
                    cbAddEmployee.setSelected(false);
                    cbEmployePassChange.setSelected(false);
                    cbUpdateEmployee.setSelected(false);
                }
                if (rs.getInt(18) == 1) {
                    cbOrgManage.setSelected(true);
                } else {
                    cbOrgManage.setSelected(false);
                }

//                if(rs.getInt(17) == 1){cb.setSelected(true);}else{cbAddProduct.setSelected(false);}
                if (rs.getInt(19) == 1) {
                    cbChangePassword.setSelected(true);
                } else {
                    cbChangePassword.setSelected(false);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbSotckMangeOnClick(MouseEvent event) {

    }

    private void stockManeCbOperation() {

        if (cbStockManegement.isSelected()) {
            cbAddProduct.setSelected(true);
            cbAddBrand.setSelected(true);
            cbAddCatagory.setSelected(true);
            cbAddSupplyer.setSelected(true);
            cbAddUnit.setSelected(true);
            cbUpdateBrands.setSelected(true);
            cbUpdateCatagory.setSelected(true);
            cbUpdateSupplyert.setSelected(true);
            cbUpdateProduct.setSelected(true);
            cbUpdateUnit.setSelected(true);
            cbMenageRMA.setSelected(true);
        } else if (!cbStockManegement.isSelected()) {
            cbAddProduct.setSelected(false);
            cbAddBrand.setSelected(false);
            cbAddCatagory.setSelected(false);
            cbAddSupplyer.setSelected(false);
            cbAddUnit.setSelected(false);
            cbUpdateBrands.setSelected(false);
            cbUpdateCatagory.setSelected(false);
            cbUpdateSupplyert.setSelected(false);
            cbUpdateProduct.setSelected(false);
            cbUpdateUnit.setSelected(false);
            cbMenageRMA.setSelected(false);
        }

    }
}
