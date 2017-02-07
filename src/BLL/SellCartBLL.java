package BLL;

import DAL.SellCart;
import Getway.SellCartGerway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/16/15.
 */
public class SellCartBLL {

    SellCartGerway sellCartGerway = new SellCartGerway();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void sell(SellCart sellCart) {

        updateCurrentQuentity(sellCart);
        sellCartGerway.save(sellCart);

    }

    public void updateCurrentQuentity(SellCart sellCart) {
        System.out.println("In Update");
        int oQ = Integer.parseInt(sellCart.oldQuentity);
        int nQ = Integer.parseInt(sellCart.quantity);
        int uQ = (oQ - nQ);
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        try {
            System.out.println("In Processing Update");
            pst = con.prepareStatement("update "+db+".Products set Quantity=? where Id=?");
            pst.setString(1, updatedQuentity);
            pst.setString(2, sellCart.Id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update Complate");
    }

}
