/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import controller.application.employe.AddEmployeController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.application.stock.ViewSupplyerController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author rifat
 */
public class SQL {
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    
    
    public void registration(String id,String userName,String fullName,
            String emailAddress,String contactNumber,String salary,
            String address,String password,int status,
            String date,String crratorId,String imagePath ){
        con = dbCon.geConnection();
        
        try {
            pst = con.prepareStatement("insert into "+db+".User values(?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, userName);
            pst.setString(3, fullName);
            pst.setString(4, emailAddress);
            pst.setString(5, contactNumber);
            pst.setString(6, salary);
            pst.setString(7, address);
            pst.setString(8, password);
            pst.setString(9, "1");
            if(imagePath!=null){
                System.out.println("I am hear");
                InputStream is;
                try {
                    is = new FileInputStream(new File(imagePath));
                    pst.setBlob(10, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AddEmployeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }else{
                pst.setBlob(10, (Blob) null);
            }
            
            pst.setString(11, LocalDate.now().toString());
            pst.setString(12, id);
            
            pst.executeUpdate();
            pst.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void userPermissionUpdate(int id){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement(null);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void basicPermission(String usrName){
        DBConnection dbc = new DBConnection();
        con = dbc.geConnection();

        try {
            pst = con.prepareStatement("Select Id FROM "+db+".User where UsrName=?");
            pst.setString(1, usrName);
            rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into "+db+".UserPermission values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setInt(2, 1);
                pst.setInt(3, 1);
                pst.setInt(4, 1);
                pst.setInt(5, 1);
                pst.setInt(6, 1);
                pst.setInt(7, 1);
                pst.setInt(8, 1);
                pst.setInt(9, 1);
                pst.setInt(10, 1);
                pst.setInt(11, 1);
                pst.setInt(12, 1);
                pst.setInt(13, 1);
                pst.setInt(14, 1);
                pst.setInt(15, 1);
                pst.setInt(16, 1);
                pst.setInt(17, 1);
                pst.setInt(18, 1);
                pst.setInt(19, 1);
                pst.setInt(20, rs.getInt("Id"));

                pst.executeUpdate();
            }con.close();
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creatorNameFindar( String creatorId, Label creatorName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".User where Id=?");
            pst.setString(1, creatorId);
            rs = pst.executeQuery();
            while (rs.next()) {
                creatorName.setText(rs.getString(2));
            }con.close();
            pst.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(ViewSupplyerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName(String id, String name, String tableName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+tableName+" where Id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                name = rs.getString(2);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public String getIdNo( String name,String id, String tableName,String fieldName){

        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+"."+tableName+" where "+fieldName+" =?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
    
    public String getBrandID(String supplyerId,String brandId,String brandName){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".Brands where SupplyerId=? and BrandName=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandName);
            rs  = pst.executeQuery();
            while(rs.next()){
                brandId = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brandId;
    }
    
    public String getCatagoryId(String supplyerId,String brandId,String catagoryId,String catagoryName){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".Catagory where SupplyerId=? and BrandId=? and CatagoryName=?");
            pst.setString(1, supplyerId);
            pst.setString(2, brandId);
            pst.setString(3, catagoryName);
            rs  = pst.executeQuery();
            while(rs.next()){
                catagoryId = rs.getString(1);
            }con.close();
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catagoryId;
    }
    
    public String getDayes(String rmaDayes, String id){
        con = dbCon.geConnection();;
        try {
            pst = con.prepareStatement("select * from "+db+".RMA where id=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                rmaDayes = rs.getString(3);
            }con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rmaDayes;
    }

    
}
