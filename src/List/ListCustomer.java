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
public class ListCustomer {
    
    public String id;
    public String customerName;
    public String customerContNo;
    public String customerAddress;
    public String totalBuy;
    public String addBy;
    public String addedDate;

    public ListCustomer(String id, String customerName, String customerContNo, String customerAddress, String totalBuy, String addBy, String addedDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerContNo = customerContNo;
        this.customerAddress = customerAddress;
        this.totalBuy = totalBuy;
        this.addBy = addBy;
        this.addedDate = addedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContNo() {
        return customerContNo;
    }

    public void setCustomerContNo(String customerContNo) {
        this.customerContNo = customerContNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(String totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
    
    
    
}
