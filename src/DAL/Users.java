package DAL;

import List.ListEmployee;
import dataBase.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/2/15.
 */
public class Users {

    public ObservableList<String> allUser = FXCollections.observableArrayList();

    public String id;
    public String userName;
    public String fullName;
    public String emailAddress;
    public String contactNumber;
    public String salary;
    public String address;
    public String password;
    public String status;
    public String imagePath;
    public Blob userImage;
//    public File userImage;
    public String date;
    public String creatorId;
    public Image image;

    public ObservableList<ListEmployee> employeeLists = FXCollections.observableArrayList();


}
