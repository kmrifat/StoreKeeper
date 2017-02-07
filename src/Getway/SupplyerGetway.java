/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.Supplyer;
import List.ListSupplyer;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author rifat
 */
public class SupplyerGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Supplyer supplyer) {
        con = dbCon.geConnection();
        if (isUniqSupplyerName(supplyer)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".Supplyer values(?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, supplyer.supplyerName);
                pst.setString(3, supplyer.supplyerContactNumber);
                pst.setString(4, supplyer.supplyerAddress);
                pst.setString(5, supplyer.supplyerDescription);
                pst.setString(6, supplyer.creatorId);
                pst.setString(7, LocalDate.now().toString());
                pst.executeUpdate();
                con.close();
                pst.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Supplier" + "  '" + supplyer.supplyerName + "' " + "Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void view(Supplyer supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".Supplyer");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);

                supplyer.supplyerDetails.addAll(new ListSupplyer(supplyer.id, supplyer.supplyerName, supplyer.supplyerContactNumber, supplyer.supplyerAddress, supplyer.supplyerDescription, supplyer.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
//            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception");
        }
    }

    public void searchView(Supplyer supplyer) {
        supplyer.supplyerDetails.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Supplyer where SupplyerName like ? or SupplyerPhoneNumber like ? ORDER BY SupplyerName");
            pst.setString(1, "%" + supplyer.supplyerName + "%");
            pst.setString(2, "%" + supplyer.supplyerName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);
                supplyer.supplyerDetails.addAll(new ListSupplyer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Supplyer supplyer) {
        System.out.println("name :" + supplyer.supplyerName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Supplyer where id=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                supplyer.id = rs.getString(1);
                supplyer.supplyerName = rs.getString(2);
                supplyer.supplyerContactNumber = rs.getString(3);
                supplyer.supplyerAddress = rs.getString(4);
                supplyer.supplyerDescription = rs.getString(5);
                supplyer.creatorId = rs.getString(6);
                supplyer.date = rs.getString(7);
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Supplyer supplyer) {
        System.out.println("we are in update");
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".Supplyer where Id=? and SupplyerName=?");
            pst.setString(1, supplyer.id);
            pst.setString(2, supplyer.supplyerName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Into the loop");
                updateNow(supplyer);
                rs.close();
                pst.close();
                con.close();
                return;
            }
            rs.close();
            con.close();
            pst.close();
            if (isUniqSupplyerName(supplyer)) {
                System.out.println("Out of the loop");
                updateNow(supplyer);
                rs.close();
                con.close();
                pst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Supplyer supplyer) {
        con = dbCon.geConnection();
        try {

            con = dbCon.geConnection();
            pst = con.prepareCall("SELECT * FROM "+db+".Brands WHERE SupplyerId=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("This supplier provide some brands, please delete these brand first! Is that nessary to delete this supplyer ? \nif not you can update supplyer as much you can");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return;
            }
            rs.close();
            con.close();
            pst.close();
            deleteParmanently(supplyer);
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isUniqSupplyerName(Supplyer supplyer) {
        con = dbCon.geConnection();
        boolean uniqSupplyer = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select SupplyerName from "+db+".Supplyer where SupplyerName=?");
            pst.setString(1, supplyer.supplyerName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Supplier" + "  '" + supplyer.supplyerName + "' " + "Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return uniqSupplyer;
            }
            rs.close();
            con.close();
            pst.close();
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

    public void updateNow(Supplyer supplyer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".Supplyer set SupplyerName=? , SupplyerPhoneNumber=?,SupplyerAddress=? ,SuplyerDescription=? where Id=?");
            pst.setString(1, supplyer.supplyerName);
            pst.setString(2, supplyer.supplyerContactNumber);
            pst.setString(3, supplyer.supplyerAddress);
            pst.setString(4, supplyer.supplyerDescription);
            pst.setString(5, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Supplier" + "  '" + supplyer.supplyerName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParmanently(Supplyer supplyer) {
        con = dbCon.geConnection();
        try {
            System.out.println("and i am hear");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".Supplyer where Id=?");
            pst.setString(1, supplyer.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isUpdate(Supplyer supplyer) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Supplyer where Id=?");
            pst.setString(1, supplyer.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    public boolean isNotUse(Supplyer supplyer) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Brands where SupplyerId=?");
            pst.setString(1, supplyer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("This Supplier supplied  '" + rs.getString(2) + "' brand \n delete brand first");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
                return isNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplyerGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
