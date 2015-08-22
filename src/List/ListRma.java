package List;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by rifat on 7/31/15.
 */
public class ListRma {

    public String ramId;
    public String rmaName;
    public String rmaDays;
    public String rmaComment;
    public String creatorName;
    public String date;

    public ListRma(String ramId, String rmaName, String rmaDays, String rmaComment, String creatorName, String date) {
        this.ramId = ramId;
        this.rmaName = rmaName;
        this.rmaDays = rmaDays;
        this.rmaComment = rmaComment;
        this.creatorName = creatorName;
        this.date = date;
    }

    public String getRamId() {
        return ramId;
    }

    public void setRamId(String ramId) {
        this.ramId = ramId;
    }

    public String getRmaName() {
        return rmaName;
    }

    public void setRmaName(String rmaName) {
        this.rmaName = rmaName;
    }

    public String getRmaDays() {
        return rmaDays;
    }

    public void setRmaDays(String rmaDays) {
        this.rmaDays = rmaDays;
    }

    public String getRmaComment() {
        return rmaComment;
    }

    public void setRmaComment(String rmaComment) {
        this.rmaComment = rmaComment;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
