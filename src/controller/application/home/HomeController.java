/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.home;

import Getway.CurrentProductGetway;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class HomeController implements Initializable {
    @FXML
    private Label lblTotalItem;
    @FXML
    private Label lblStockValue;
    @FXML
    private Label lblTotalSupplyer;
    @FXML
    private Label lblTotalEmployee;
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Label lblTotalSell;
    @FXML
    private Label lblSellValue;
    @FXML
    private Label lblOrgName;
    @FXML
    private Text txtOrgAddress;
    @FXML
    private Text txtorgPhoneNumber;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valueCount();
        totalCount();
    }    
    
    public void valueCount(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select sum(PursesPrice) from "+db+".Products");
            rs = pst.executeQuery();
            while (rs.next()) {
                lblStockValue.setText(rs.getString(1));
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void totalCount(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select count(*) from "+db+".Sell");
            rs = pst.executeQuery();
            while (rs.next()) {
                lblTotalSell.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from "+db+".Supplyer");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalSupplyer.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from "+db+".Products");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalItem.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select sum(TotalPrice) from "+db+".Sell");
            rs = pst.executeQuery();
            while(rs.next()){
                lblSellValue.setText(rs.getString(1));
            }
            pst = con.prepareStatement("select count(*) from "+db+".User");
            rs = pst.executeQuery();
            while(rs.next()){
                lblTotalEmployee.setText(rs.getString(1));
            }
            pst =con.prepareStatement("select * from "+db+".Organize where Id=1");
            rs = pst.executeQuery();
            while(rs.next()){
                lblOrgName.setText(rs.getString(2));
                txtOrgAddress.setText(rs.getString(5));
                txtorgPhoneNumber.setText(rs.getString(4));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
