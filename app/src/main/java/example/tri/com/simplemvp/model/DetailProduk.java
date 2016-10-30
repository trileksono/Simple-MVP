package example.tri.com.simplemvp.model;

/**
 * Created by tri on 10/16/16.
 */

public class DetailProduk {

    private String status;
    private String title;
    private String img;
    private DataDetail data;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public DataDetail getData() {
        return data;
    }

    public void setData(DataDetail data) {
        this.data = data;
    }
}
