package example.tri.com.simplemvp.model;

import java.util.List;

/**
 * Created by tri on 10/16/16.
 */

public class SearchBrand {

    private String status;
    private String title;
    private List<DataSearch> data;
    private int total_page;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DataSearch> getData() {
        return data;
    }

    public void setData(List<DataSearch> data) {
        this.data = data;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
}
