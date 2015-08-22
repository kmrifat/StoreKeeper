package DAL;


import List.ListCustomer;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.dialog.Dialog;

/**
 * Created by rifat on 8/2/15.
 */
public class Customer {

    public String id;
    public String customerName;
    public String customerConNo;
    public String customerAddress;
    public String totalBuy;
    public String date;
    public String userId;
    public String userName;

    public ObservableList<ListCustomer> customerList = FXCollections.observableArrayList();

}