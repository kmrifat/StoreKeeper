/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rifat
 */
public class SQLightConnection {

    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    String table = "CREATE TABLE if not exists mysqlInfo "
            + "(ID INT PRIMARY KEY     NOT NULL,"
            + " hostName          VARCHAR(30) , "
            + " portName          VARCHAR(30) , "
            + " userName        VARCHAR(30) , "
            + " password         VARCHAR(30) )";

    String demoValue = "insert into mysqlInof values(?,?,?,?,?)";

    public Connection sqliteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:storekeeperConnection.db");
            System.out.println("SQLIGHT DATABASE OPENED SUCESSFULY");
            pst = con.prepareStatement(table);
            pst.execute();
            System.out.println("SUcessfuly crrate table");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLightConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLightConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

}
