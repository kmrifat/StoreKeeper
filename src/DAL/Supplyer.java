package DAL;

import List.ListSupplyer;
import dataBase.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/1/15.
 */
public class Supplyer {

    public String id;
    public String supplyerName;
    public String supplyerContactNumber;
    public String supplyerAddress;
    public String supplyerDescription;
    public String creatorId;
    public String date;

//    public List<ListSupplyer> rowSupplyer;
    public ObservableList<ListSupplyer> supplyerDetails = FXCollections.observableArrayList();




}
