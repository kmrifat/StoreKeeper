package DAL;


import List.ListRma;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rifat on 8/2/15.
 */
public class RMA {



    public String id;
    public String rmaName;
    public String rmaDays;
    public String rmaComment;
    public String creatorId;
    public String creatorName;
    public String date;

    public ObservableList<ListRma> rmaDetails = FXCollections.observableArrayList();




}