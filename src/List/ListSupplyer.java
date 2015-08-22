/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;


/**
 *
 * @author rifat
 */
public class ListSupplyer {
    
    public String supplyerId;
    public String supplyerName;
    public String supplyerPhoneNumber;
    public String supplyerAddress;
    public String supplyerDescription;
    public String creatorId;
    public String dataOfjoining;

    public ListSupplyer(String supplyerId, String supplyerName) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
    }

    public void setSupplyerAddress(String supplyerAddress) {
        this.supplyerAddress = supplyerAddress;
    }



    public String getSupplyerPhoneNumber() {
        return supplyerPhoneNumber;
    }

    public String getSupplyerAddress() {
        return supplyerAddress;
    }

    public String getSupplyerDescription() {
        return supplyerDescription;
    }

    public String getDataOfjoining() {
        return dataOfjoining;
    }

    public ListSupplyer(String supplyerId, String supplyerName, String supplyerPhoneNumber, String supplyerAddress, String supplyerDescription, String dataOfjoining) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
        this.supplyerPhoneNumber = supplyerPhoneNumber;
        this.supplyerAddress = supplyerAddress;
        this.supplyerDescription = supplyerDescription;
        this.dataOfjoining = dataOfjoining;
    }





    public String getSupplyerId() {
        return supplyerId;
    }

    public void setSupplyerId(String supplyerId) {
        this.supplyerId = supplyerId;
    }

    public String getSupplyerName() {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
    }

}
