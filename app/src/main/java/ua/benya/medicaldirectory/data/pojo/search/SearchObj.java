package ua.benya.medicaldirectory.data.pojo.search;

import java.io.Serializable;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class SearchObj implements Serializable {
    private int id;
    private String title;
    private String type;
    private boolean isAnalog;
    private String analogTxt;

    public String getAnalogTxt() {
        return analogTxt;
    }

    public void setAnalogTxt(String analogTxt) {
        this.analogTxt = analogTxt;
    }

    public boolean isAnalog() {
        return isAnalog;
    }

    public void setAnalog(boolean analog) {
        isAnalog = analog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
