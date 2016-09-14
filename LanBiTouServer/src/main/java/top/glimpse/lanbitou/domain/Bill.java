package top.glimpse.lanbitou.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单实体类
 * Created by Henvealf on 16-5-14.
 */
public class Bill implements Serializable{

    private int id;
    private int uid;                 //用户Id
    private String type;             //收支类型 10
    private double money;            //收或支多少钱.
    private String folder;           //所属的文件夹 50
    private String remark;           //备注 300
    private String billDate;           //账单时间,并非创建时间,由用户指定
    private boolean inClouded;        //是否同步


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public boolean isInClouded() {
        return inClouded;
    }

    public void setInClouded(boolean inClouded) {
        this.inClouded = inClouded;
    }
}
