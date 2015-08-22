package List;

/**
 * Created by rifat on 8/4/15.
 */
public class ListBrands {
    public String id;
    public String brandName;
    public String brandComment;
    public String supplyerName;
    public String creatorId;
    public String date;

    public ListBrands(String id, String brandName, String brandComment, String supplyerName, String creatorId, String date) {
        this.id = id;
        this.brandName = brandName;
        this.brandComment = brandComment;
        this.supplyerName = supplyerName;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandComment() {
        return brandComment;
    }

    public void setBrandComment(String brandComment) {
        this.brandComment = brandComment;
    }

    public String getSupplyerName() {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
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
