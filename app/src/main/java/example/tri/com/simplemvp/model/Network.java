package example.tri.com.simplemvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tri on 10/16/16.
 */

public class Network {

    private String technology;
    @SerializedName("2g_bands")
    private String value2g_bands;
    @SerializedName("3g_bands")
    private String value3g_bands;
    @SerializedName("4g_bands")
    private String value4g_bands;
    private String speed;
    private String gprs;
    private String edge;

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getValue2g_bands() {
        return value2g_bands;
    }

    public void setValue2g_bands(String value2g_bands) {
        this.value2g_bands = value2g_bands;
    }

    public String getValue3g_bands() {
        return value3g_bands;
    }

    public void setValue3g_bands(String value3g_bands) {
        this.value3g_bands = value3g_bands;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getEdge() {
        return edge;
    }

    public String getValue4g_bands() {
        return value4g_bands;
    }

    public void setValue4g_bands(String value4g_bands) {
        this.value4g_bands = value4g_bands;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }
}
