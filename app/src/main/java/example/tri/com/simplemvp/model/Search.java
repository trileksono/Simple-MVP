package example.tri.com.simplemvp.model;

import java.util.List;

/**
 * Created by tri on 10/16/16.
 */

public class Search {

    private String status;
    private List<DataSearch> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataSearch> getData() {
        return data;
    }

    public void setData(List<DataSearch> data) {
        this.data = data;
    }
}
