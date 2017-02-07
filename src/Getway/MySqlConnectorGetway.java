/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.MysqlConnector;
import dataBase.DBProperties;
import dataBase.SQLightConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rifat
 */
public class MySqlConnectorGetway {

    SQLightConnection qLightConnection = new SQLightConnection();
    Connection con = qLightConnection.sqliteConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(MysqlConnector connector) {
        try {
            pst = con.prepareStatement("insert into mysqlInfo values(?,?,?,?,?)");
            pst.setInt(1, 1);
            pst.setString(2, connector.hostName);
            pst.setString(3, connector.portName);
            pst.setString(4, connector.userName);
            pst.setString(5, connector.password);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnectorGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void view(MysqlConnector connector) {
        try {
            pst = con.prepareStatement("select * from mysqlInfo where Id=1");
            rs = pst.executeQuery();
            while(rs.next()){
                connector.hostName = rs.getString(2);
                connector.portName = rs.getString(3);
                connector.userName = rs.getString(4);
                connector.password = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnectorGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
