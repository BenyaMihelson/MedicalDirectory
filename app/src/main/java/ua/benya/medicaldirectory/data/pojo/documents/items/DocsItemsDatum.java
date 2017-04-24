package ua.benya.medicaldirectory.data.pojo.documents.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class DocsItemsDatum implements Serializable {

    @SerializedName("data")
    @Expose
    private List<DocsItems> data = new ArrayList<DocsItems>();
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
    public List<DocsItems> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<DocsItems> data) {
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
