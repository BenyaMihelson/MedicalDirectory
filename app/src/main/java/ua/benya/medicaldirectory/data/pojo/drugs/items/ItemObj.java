package ua.benya.medicaldirectory.data.pojo.drugs.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shipohvost on 15.11.2016.
 */

public class ItemObj {
    @SerializedName("data")
    @Expose
    private List<Item> data = new ArrayList<Item>();
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
    public List<Item> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Item> data) {
        this.data = data;
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
