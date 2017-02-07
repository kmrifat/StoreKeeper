package Getway;

import DAL.SellCart;
import List.ListSold;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/16/15.
 */
public class SellCartGerway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();


    public void save(SellCart sellCart){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into "+db+".Sell values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, sellCart.sellID);
            pst.setString(3, sellCart.customerID);
            pst.setString(4, sellCart.productID);
            pst.setString(5, sellCart.pursesPrice);
            pst.setString(6, sellCart.sellPrice);
            pst.setString(7, sellCart.quantity);
            pst.setString(8, sellCart.totalPrice);
            pst.setString(9, sellCart.warrentyVoidDate);
            pst.setString(10, sellCart.sellerID);
            pst.setString(11, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            
//            Dialogs.create().title(null).masthead("Soled").message("Soled Sucessfuly").styleClass(Dialog.STYLE_CLASS_UNDECORATED).showInformation();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(SellCart sellCart){
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from "+db+".Sell");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void firstTenView(SellCart sellCart){
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from "+db+".Sell limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchView(SellCart sellCart){
        con = dbCon.geConnection();
        sellCart.carts.clear();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from "+db+".Sell where SellId like ?");
            pst.setString(1, "%" + sellCart.sellID + "%");
            rs = pst.executeQuery();
            while (rs.next()){
                sellCart.Id = rs.getString(1);
                sellCart.sellID = rs.getString(2);
                sellCart.customerID = rs.getString(3);
                sellCart.productID = rs.getString(4);
                sellCart.pursesPrice = rs.getString(5);
                sellCart.sellPrice = rs.getString(6);
                sellCart.quantity = rs.getString(7);
                sellCart.totalPrice = rs.getString(8);
                sellCart.warrentyVoidDate = rs.getString(9);
                sellCart.sellerID = rs.getString(10);
                sellCart.sellDate = rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                
                sellCart.soldList.addAll(new ListSold(sellCart.Id,sellCart.sellID ,sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate));
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
