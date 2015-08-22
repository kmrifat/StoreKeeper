package BLL;

import DAL.CurrentProduct;
import DAL.Customer;
import Getway.CurrentProductGetway;
import dataBase.DBConnection;
import dataBase.SQL;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.dialog.Dialog;

/**
 * Created by rifat on 8/15/15.
 */
public class CurrentProductBLL {

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    SQL sql = new SQL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();

    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            currentProductGetway.save(currentProduct);
        }
        
    }

    public void update(CurrentProduct currentProduct) {
        if(isNotNull(currentProduct)){
        if (isUpdate(currentProduct)) {
            if (checkUpdateCondition(currentProduct)) {
                currentProductGetway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                currentProductGetway.update(currentProduct);
            }
        }
        }
    }

    public boolean isUniqName(CurrentProduct currentProduct) {
        System.out.println("WE ARE IS IS UNIT NAME");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from Products where ProductId=?");
            pst.setString(1, currentProduct.productId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Dialogs.create().title("Warning").lightweight().masthead("Product id not Uniq").message("").showWarning();
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }

    public boolean isUpdate(CurrentProduct currentProduct) {
        System.out.println("WE ARE IS IS UPDTE");
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from Products where Id=? and ProductId=? and ProductName=? and Quantity=? and Description=? and SupplyerId=? and BrandId=? "
                    + "and CatagoryId=? and UnitId=? and PursesPrice=? and SellPrice=? and RMAId=? and Date=?");
            pst.setString(1, currentProduct.id);
            pst.setString(2, currentProduct.productId);
            pst.setString(3, currentProduct.productName);
            pst.setString(4, currentProduct.quantity);
            pst.setString(5, currentProduct.description);
            pst.setString(6, currentProduct.supplierId);
            pst.setString(7, currentProduct.brandId);
            pst.setString(8, currentProduct.catagoryId);
            pst.setString(9, currentProduct.unitId);
            pst.setString(10, currentProduct.pursesPrice);
            pst.setString(11, currentProduct.sellPrice);
            pst.setString(12, currentProduct.rmaId);
            pst.setString(13, currentProduct.date);
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    public boolean checkUpdateCondition(CurrentProduct currentProduct) {
        boolean isTrueUpdate = false;
        if (isUpdate(currentProduct)) {
            try {
                pst = con.prepareStatement("select * from Products where id=? and ProductId=?");
                pst.setString(1, currentProduct.id);
                pst.setString(2, currentProduct.productId);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isTrueUpdate;
    }

    public boolean isNotNull(CurrentProduct currentProduct) {
        
        boolean isNotNull = false;
        if (currentProduct.productId.isEmpty() || currentProduct.sellPrice.isEmpty() || currentProduct.quantity.isEmpty()) {
            Dialogs.create().title(null).masthead("Null not tolarate").message("Please fill requrer field").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showError();
            return isNotNull;
        }
        return isNotNull;
    }

    public Object delete(CurrentProduct currentProduct){
        if(currentProductGetway.isNotSoled(currentProduct)){
            currentProductGetway.delete(currentProduct);
        }else{
            //noting
        }
        return currentProduct;
    }
    

}
