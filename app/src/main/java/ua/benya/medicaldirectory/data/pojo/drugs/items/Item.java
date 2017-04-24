package ua.benya.medicaldirectory.data.pojo.drugs.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shipohvost on 15.11.2016.
 */


public class Item implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pharmacology")
    @Expose
    private String pharmacology;
    @SerializedName("testimony")
    @Expose
    private String testimony;
    @SerializedName("contraindications")
    @Expose
    private String contraindications;
    @SerializedName("dosage")
    @Expose
    private String dosage;
    @SerializedName("side_effect")
    @Expose
    private String sideEffect;
    @SerializedName("counterparts")
    @Expose
    private String counterparts;
    @SerializedName("mkb10")
    @Expose
    private String mkb10;
    @SerializedName("additional")
    @Expose
    private String additional;



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
    public String getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     * The parent_id
     */
    public void setParentId(String parentId) {
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

    /**
     *
     * @return
     * The pharmacology
     */
    public String getPharmacology() {
        return pharmacology;
    }

    /**
     *
     * @param pharmacology
     * The pharmacology
     */
    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    /**
     *
     * @return
     * The testimony
     */
    public String getTestimony() {
        return testimony;
    }

    /**
     *
     * @param testimony
     * The testimony
     */
    public void setTestimony(String testimony) {
        this.testimony = testimony;
    }

    /**
     *
     * @return
     * The contraindications
     */
    public String getContraindications() {
        return contraindications;
    }

    /**
     *
     * @param contraindications
     * The contraindications
     */
    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    /**
     *
     * @return
     * The dosage
     */
    public String getDosage() {
        return dosage;
    }

    /**
     *
     * @param dosage
     * The dosage
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     *
     * @return
     * The sideEffect
     */
    public String getSideEffect() {
        return sideEffect;
    }

    /**
     *
     * @param sideEffect
     * The side_effect
     */
    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    /**
     *
     * @return
     * The counterparts
     */
    public String getCounterparts() {
        return counterparts;
    }

    /**
     *
     * @param counterparts
     * The counterparts
     */
    public void setCounterparts(String counterparts) {
        this.counterparts = counterparts;
    }

    /**
     *
     * @return
     * The mkb10
     */
    public String getMkb10() {
        return mkb10;
    }

    /**
     *
     * @param mkb10
     * The mkb10
     */
    public void setMkb10(String mkb10) {
        this.mkb10 = mkb10;
    }

    /**
     *
     * @return
     * The additional
     */
    public String getAdditional() {
        return additional;
    }

    /**
     *
     * @param additional
     * The additional
     */
    public void setAdditional(String additional) {
        this.additional = additional;
    }

}