/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rifat
 */
public class ListCatagory {
    public String id;
    public String catagoryName;
    public String catagoryDescription;
    public String brandId;
    public String supplyerId;
    public String creatorId;
    public String date;

    public ListCatagory(String id, String catagoryName, String catagoryDescription, String brandId, String supplyerId, String creatorId, String date) {
        this.id = id;
        this.catagoryName = catagoryName;
        this.catagoryDescription = catagoryDescription;
        this.brandId = brandId;
        this.supplyerId = supplyerId;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getCatagoryDescription() {
        return catagoryDescription;
    }

    public void setCatagoryDescription(String catagoryDescription) {
        this.catagoryDescription = catagoryDescription;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getSupplyerId() {
        return supplyerId;
    }

    public void setSupplyerId(String supplyerId) {
        this.supplyerId = supplyerId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}