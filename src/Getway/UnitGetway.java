/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.Supplyer;
import DAL.Unit;
import List.ListUnit;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
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
 *
 * @author rifat
 */
public class UnitGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    SQL sql = new SQL();

    public void save(Unit unit) {
        con = dbCon.geConnection();

        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("insert into "+db+".Unit values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, unit.unitName);
            pst.setString(3, unit.unitDescription);
            pst.setString(4, unit.creatorId);
            pst.setString(5, LocalDate.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Unit" + "  '" + unit.unitName + "' " + "Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void view(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".Unit");
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
                unit.creatorId = rs.getString(4);
                unit.date = rs.getString(5);
                unit.creatorName = sql.getName(unit.creatorId, unit.creatorName, "User");
                unit.unitDetails.addAll(new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Unit unit) {
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Unit where id=?");
            pst.setString(1, unit.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
            }
            pst.close();
            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchView(Unit unit) {
        con = dbCon.geConnection();
        unit.unitDetails.clear();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Unit where UnitName like ? ORDER BY UnitName");

            pst.setString(1, "%" + unit.unitName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                unit.id = rs.getString(1);
                unit.unitName = rs.getString(2);
                unit.unitDescription = rs.getString(3);
                unit.creatorId = rs.getString(4);
                unit.date = rs.getString(5);
                unit.creatorName = sql.getName(unit.creatorId, unit.creatorName, "User");
                unit.unitDetails.addAll(new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date));
            }
            pst.close();
            con.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".Unit where Id=? and UnitName=?");
            pst.setString(1, unit.id);
            pst.setString(2, unit.unitName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Into the loop");
                updateNow(unit);
                return;
            }
            pst.close();
            con.close();
            rs.close();
            if (isUniqName(unit)) {
                System.out.println("Out of the loop");
                updateNow(unit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNow(Unit unit) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".Unit set UnitName=? , UnitDescription=? where Id=?");
            pst.setString(1, unit.unitName);
            pst.setString(2, unit.unitDescription);
            pst.setString(3, unit.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Unit" + "  '" + unit.unitName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Unit unit) {
        con = dbCon.geConnection();
        deleteParmanently(unit);
    }

    public boolean isUniqName(Unit unit) {
        con = dbCon.geConnection();
        boolean uniqBrand = false;
        try {
            pst = con.prepareCall("select * from "+db+".Unit where UnitName=?");
            pst.setString(1, unit.unitName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : Already exist");
                alert.setContentText("Unit" + "  '" + unit.unitName + "' " + "Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return uniqBrand;
            }
            pst.close();
            con.close();
            rs.close();
            uniqBrand = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqBrand;
    }

    public void deleteParmanently(Unit unit) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareCall("delete from "+db+".Unit where Id=?");
            pst.setString(1, unit.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isNotUse(Unit unit) {
        con = dbCon.geConnection();
        boolean isNotUse = false;

        try {
            pst = con.prepareStatement("select * from "+db+".Products where UnitId=?");
            pst.setString(1, unit.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("This Unit use in '" + rs.getString(2) + "' product \n delete product first");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return isNotUse;
            }
            pst.close();
            rs.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(UnitGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }

}
