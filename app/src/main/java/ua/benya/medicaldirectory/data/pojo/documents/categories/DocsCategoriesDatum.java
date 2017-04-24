package ua.benya.medicaldirectory.data.pojo.documents.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class DocsCategoriesDatum implements Serializable {

    @Expose
    private List<DocsCategories> data = new ArrayList<DocsCategories>();

    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gen_time")
    @Expose
    private Double genTime;

    /**
     *
     * @return
     * The data
     */
    public List<DocsCategories> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<DocsCategories> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The lastUpdate
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     * The lastUpdate
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The genTime
     */
    public Double getGenTime() {
        return genTime;
    }

    /**
     *
     * @param genTime
     * The gen_time
     */
    public void setGenTime(Double genTime) {
        this.genTime = genTime;
    }
}
