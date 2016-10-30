package example.tri.com.simplemvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tri on 10/16/16.
 */

public class Sound {
    private String alert_types;
    private String loudspeaker_;
    @SerializedName("35mm_jack_")
    private String value35mm_jack_;

    public String getAlert_types() {
        return alert_types;
    }

    public void setAlert_types(String alert_types) {
        this.alert_types = alert_types;
    }

    public String getLoudspeaker_() {
        return loudspeaker_;
    }

    public void setLoudspeaker_(String loudspeaker_) {
        this.loudspeaker_ = loudspeaker_;
    }

    public String getValue35mm_jack_() {
        return value35mm_jack_;
    }

    public void setValue35mm_jack_(String value35mm_jack_) {
        this.value35mm_jack_ = value35mm_jack_;
    }
}
