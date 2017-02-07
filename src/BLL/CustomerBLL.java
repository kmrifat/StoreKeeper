package BLL;

import DAL.Customer;
import Getway.CustomerGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Created by rifat on 8/15/15.
 */
public class CustomerBLL {
    SQL sql = new SQL();
    CustomerGetway customerGetway = new CustomerGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Customer customer){
        if(isUniqName(customer)){
            customerGetway.save(customer);
        }
    }

    public void update(Customer customer){
        if(isUpdate(customer)){
            if(isSame(customer)){
                customerGetway.update(customer);
            }else if(isUniqName(customer)){
                customerGetway.update(customer);
            }
        }
    }


    public boolean isUniqName(Customer customer) {
        boolean inUniqName = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Customer where CustomerName=? and CustomerContNo=?");
            pst.setString(1, customer.customerName);
            pst.setString(2, customer.customerConNo);
            rs = pst.executeQuery();
            while (rs.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("This Customer name or phone number already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                

                return inUniqName;
            }
            inUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inUniqName;
    }

    public boolean isUpdate(Customer customer) {
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Customer where Id=? and CustomerName=? and CustomerContNo=? and CustomerAddress=?");
            pst.setString(1,customer.id);
            pst.setString(2,customer.customerName);
            pst.setString(3,customer.customerConNo);
            pst.setString(4,customer.customerAddress);
            rs = pst.executeQuery();
            while (rs.next()){

                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    private boolean isSame(Customer customer){
        boolean isSame = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Customer where id=? and CustomerName=? and CustomerContNo=?");
            pst.setString(1,customer.id);
            pst.setString(2,customer.customerName);
            pst.setString(3,customer.customerConNo);
            rs = pst.executeQuery();
            while (rs.next()){

                return isSame=true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSame;
    }
}
