package ua.benya.medicaldirectory.data.pojo.drugs.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shipohvost on 15.11.2016.
 */


public class Child {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("parent_id")
    @Expose
    private int parentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("children")
    @Expose
    private List<SubChild> children = new ArrayList<SubChild>();


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
     * The parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     * The parent_id
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public List<SubChild> getChildren() {
        return children;
    }

    public void setChildren(List<SubChild> children) {
        this.children = children;
    }


}