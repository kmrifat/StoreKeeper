package BLL;

import DAL.RMA;
import DAL.Supplyer;
import Getway.RmaGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Created by rifat on 8/15/15.
 */
public class RmaBLL {
    SQL sql = new SQL();
    RmaGetway rmaGetway = new RmaGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(RMA rma){
        if(isUniqName(rma)){
            rmaGetway.save(rma);
        }
    }

    public void update(RMA rma){
        if(sameName(rma)){
            rmaGetway.update(rma);
        }else if (isUniqName(rma)){
            rmaGetway.update(rma);
        }
    }
    
    public Object delete(RMA rma){
        if(rmaGetway.isNotUse(rma)){
            rmaGetway.delete(rma);
        }else{
            //nothing
        }
        return rma;
    }

    public boolean sameName(RMA rma){
        boolean sameName =false;
        try {
            pst = con.prepareStatement("select * from "+db+".RMA where Id=? and RMAName=? and RMADays=?");
            pst.setString(1, rma.id);
            pst.setString(2, rma.rmaName);
            pst.setString(3, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {

                return sameName = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sameName;
    }

    public boolean isUniqName(RMA rma) {

        boolean uniqRMA = false;
        try {
            pst = con.prepareCall("select * from "+db+".RMA where RMAName=? or RMADays=?");
            pst.setString(1, rma.rmaName);
            pst.setString(2, rma.rmaDays);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("RMA" + "  '" + rma.rmaName +"/"+ rma.rmaDays + "' " + "Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return uniqRMA;
            }
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }
}
