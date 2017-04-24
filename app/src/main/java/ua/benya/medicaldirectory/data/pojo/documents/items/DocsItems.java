package ua.benya.medicaldirectory.data.pojo.documents.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class DocsItems implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("selection_id")
    @Expose
    private int selectionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The selectionId
     */
    public int getSelectionId() {
        return selectionId;
    }

    /**
     *
     * @param selectionId
     * The selection_id
     */
    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The file
     */
    public String getFile() {
        return file;
    }

    /**
     *
     * @param file
     * The file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     *
     * @return
     * The time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(String time) {
        this.time = time;
    }

}
