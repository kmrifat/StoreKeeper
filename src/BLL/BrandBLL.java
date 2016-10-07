package BLL;

import DAL.Brands;
import DAL.Supplyer;
import Getway.BrandsGetway;
import dataBase.DBConnection;
import dataBase.SQL;
//import org.controlsfx.dialog.Dialog; // update library 20161007 deprecated Dialog with Jdk8U72
//import org.controlsfx.dialog.Dialogs; // update library 20161007 deprecated Dialog with Jdk8U72

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * Created by rifat on 8/15/15.
 */
public class BrandBLL {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    BrandsGetway brandsGetway = new BrandsGetway();

    public void save(Brands brands) {
        if (isUniqName(brands)) {
            brandsGetway.save(brands);
        }
    }

    public void update(Brands brands) {
        if (isTrueUpdate(brands)) {
            brandsGetway.update(brands);
        } else if (isUniqName(brands)) {
            brandsGetway.update(brands);
        }

    }

    public void delete(Brands brands){
        if(brandsGetway.isNotUsed(brands)){
            brandsGetway.delete(brands);
        }else{
            //noting
        }
    }

    public boolean isTrueUpdate(Brands brands) {
        boolean isTreu = false;
        brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");
        System.out.println("we are in update");

        try {
            pst = con.prepareStatement("SELECT * FROM Brands WHERE BrandName =? AND SupplyerId =? AND Id =?");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplyrId);
            pst.setString(3, brands.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isTreu;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTreu;
    }


    public boolean isUniqName(Brands brands) {
        boolean uniqSupplyer = false;
        try {
            pst = con.prepareCall("select * from Brands where BrandName=? and SupplyerId=?");
            brands.supplyrId = sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");
            pst.setString(1, brands.brandName);
            pst.setString(2, brands.supplyrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                /* fixed by Karis
                // update library 20161007 deprecated Dialog with Jdk8U72
                Dialogs.create().title("Sucess")
                        .masthead("Warning")
                        .styleClass(Dialog.STYLE_CLASS_UNDECORATED)
                        .message("Brand" + "  '" + brands.brandName + "' " + "Already exist")
                        .showWarning();
                */
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Warning");
                warning.setContentText("Brand" + "  '" + brands.brandName + "' " + "Already exist");
                warning.show();
                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }

}
