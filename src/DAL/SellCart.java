package DAL;

import List.ListPreSell;
import List.ListSold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by rifat on 8/16/15.
 */
public class SellCart {
    
    public String Id;
    public String sellID;
    public String customerID;
    public String productID;
    public String pursesPrice;
    public String sellPrice;
    public String quantity;
    public String totalPrice;
    public String pursrsDate;
    public String warrentyVoidDate;
    public String sellerID;
    public String sellDate;
    public String oldQuentity;
    //Names
    
    public String customerName;
    public String sellerName;
    public String givenProductID;
    
    
    
    public ObservableList<ListPreSell> carts = FXCollections.observableArrayList();
    public ObservableList<ListSold> soldList = FXCollections.observableArrayList();

}
