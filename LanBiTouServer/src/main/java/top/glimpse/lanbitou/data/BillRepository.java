package top.glimpse.lanbitou.data;

import top.glimpse.lanbitou.domain.Bill;
import top.glimpse.lanbitou.domain.BillFolder;

import java.util.List;

/**
 * 账单的数据库操作抽象类
 * Created by Henvealf on 16-5-14.
 */
public interface BillRepository {

    /**
     * 加一个Bill
     * 其中的billDate要提前设置好,虽然数据库可以自动生成,但没有必要再写个sql语句了
     * @param bill
     * @return 返回影响的行数,即返回为1就说明插入成功
     */
    public int addOne(Bill bill);

    /**
     * 加一些Bill
     * @param billList
     */
    public void addSome(List<Bill> billList);

    /**
     * 通过id获得bill
     * @param id bill id
     */
    public Bill getOneById(int id);

    /**
     * 通过用户Id获得bill们
     * @param uid 用户ID
     * @return
     */
    public List<Bill> getSomeByUserId(int uid);

    /**
     * 根据文件夹获得bill们
     * @param folderName
     * @return
     */
    public List<Bill> getSomeByFolder(int id, String folderName);

    public List<Bill> getAllByUid(int uid);

    /**
     * 删除相应id的账单
     * @param id
     */
    public int deleteById(int id);

    /**
     * 删除
     * @param bill
     */
    public void delete(Bill bill);


    /**
     * 更新
     * @param bill
     */
    public int update(Bill bill);

    /**
     * 添加一个账单夹
     * @param billFolder
     * @return
     */
    public int addOneFolder(BillFolder billFolder);

    /**
     *  添加一些账单夹
     * @param billFolders
     * @return
     */
    public int addSomeFolders(List<BillFolder> billFolders);

    /**
     * 删除一个Bill folder
     * @param
     * @return
     */
    public int deleteByFolder(List<BillFolder> billFolderList);

    /**
     * 更新folder名字
     * @param
     * @return
     */
    public int updateFolder(BillFolder oldBillFolder, BillFolder newBillFolder);

    /**
     * 获取所有folder
     * @param uis
     * @return
     */
    public List<BillFolder>  getAllFolder(int uis);


}
