package top.glimpse.lanbitou.domain;

/**
 * Created by joyce on 16-6-8.
 */
public class NoteBook {

    private int bid;
    private int uid;
    private String name;
    private int fid;

    public NoteBook() {}

    public NoteBook(int bid, int uid, String name, int fid) {
        this.bid = bid;
        this.uid = uid;
        this.name = name;
        this.fid = fid;
    }


    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }


}
