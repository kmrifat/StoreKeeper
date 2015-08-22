package DAL;


import List.ListCatagory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by rifat on 8/2/15.
 */
public class Catagory {

    public String id;
    public String catagoryName;
    public String catagoryDescription;
    public String brandId;
    public String date;
    public String creatorId;
    public String creatorName;
    public String brandName;
    public String supplyerId;
    public String supplyerName;

    public ObservableList<ListCatagory> catagoryDetails = FXCollections.observableArrayList();


}