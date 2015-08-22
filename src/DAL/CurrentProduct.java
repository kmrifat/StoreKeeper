package DAL;

import List.ListProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by rifat on 8/2/15.
 */
public class CurrentProduct {

    public String id;
    public String productId;
    public String productName;
    public String quantity;
    public String description;
    public String supplierId;
    public String brandId;
    public String catagoryId;
    public String unitId;
    public String pursesPrice;
    public String sellPrice;
    public String rmaId;
    public String userId;
    public String date;
    public String warrentyVoidDate;
    public String supplierName;
    public String brandName;
    public String catagoryName;
    public String unitName;
    public String rmaName;
    public String userName;
    public String rmaDayesss;
    public String rmaDayes;
    public String supplyerList;
    
    public ObservableList<ListProduct> currentProductList = FXCollections.observableArrayList();
}
