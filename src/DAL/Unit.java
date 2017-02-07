package DAL;


import List.ListUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by rifat on 8/2/15.
 */
public class Unit {



    public String id;
    public String unitName;
    public String unitDescription;
    public String creatorId;
    public String creatorName;
    public String date;

    public ObservableList<ListUnit> unitDetails = FXCollections.observableArrayList();




}