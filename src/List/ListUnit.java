package List;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by rifat on 7/31/15.
 */
public class ListUnit {

    public String unitId;
    public String unitName;
    public String unitDescription;
    public String creatorName;
    public String dateOfCreation;

    public ListUnit(String unitId, String unitName, String unitDescription, String creatorName, String dateOfCreation) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.unitDescription = unitDescription;
        this.creatorName = creatorName;
        this.dateOfCreation = dateOfCreation;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
