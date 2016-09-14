package top.glimpse.lanbitou.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import top.glimpse.lanbitou.domain.Bill;
import top.glimpse.lanbitou.domain.BillFolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Henvealf on 16-5-14.
 */
@Repository
public class BillJdbcRepository implements BillRepository{

    private final static String INSERT_ONE_BILL = "insert into bills (uid, type, money, folder, remark, bill_date) values (?,?,?,?,?,?)";
    private final static String FIND_ONE_BY_ID = "select * from bills where id = ?";
    private final static String FIND_SOME_BY_FOLDER = "select * from bills where uid = ? and name = ?";
    private final static String DELETE_BY_ID = "delete from bills where id = ? ";
    private final static String DELETE_BY_FOLDER = "delete from bills where uid = ? and folder = ? ";
    private final static String FIND_ALL_BY_UID = "select * from bills where uid = ?";

    private final static String FIND_ALL_FOLDER = "select * from bill_folders where uid = ?";
    private final static String INSERT_ONE_FOLDER = "insert into bills (uid,folder,money,type,remark, bill_date) values(?,?,0,'0','0', ?)";
    private final static String UPDATE_ONE_BILL = "update bills set type = ? , money = ? , folder = ? , remark = ? ,bill_date = ? " +
                                                  " where id = ?";

    private final static String UPDATE_FOLDER_NAME = "update bills set folder = ? where bills.uid = ? and bills.folder = ? ";

    private JdbcOperations jdbcOperations;

    /**
     * 注入模板类
     * @param jdbcOperations
     */
    @Autowired

    public BillJdbcRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }



    @Override
    public int addOne(Bill bill) {
        return jdbcOperations.update(INSERT_ONE_BILL,
                            bill.getUid(),
                            bill.getType(),
                            bill.getMoney(),
                            bill.getFolder(),
                            bill.getRemark(),
                            bill.getBillDate());
    }

    @Override
    public void addSome(List<Bill> billList) {

    }

    @Override
    public Bill getOneById(int id) {
        return jdbcOperations.queryForObject(FIND_ONE_BY_ID,new BillRowMapper(),id);
    }

    @Override
    public List<Bill> getSomeByUserId(int uid) {
        return null;
    }

    @Override
    public List<Bill> getSomeByFolder(int uid, String folderName) {
        return jdbcOperations.query(FIND_SOME_BY_FOLDER,new BillRowMapper(),uid ,folderName);
    }

    @Override
    public List<Bill> getAllByUid(int uid) {
        return jdbcOperations.query(FIND_ALL_BY_UID,new BillRowMapper(), uid);
    }

    @Override
    public int deleteById(int id) {
        return jdbcOperations.update(DELETE_BY_ID,id);
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public int update(Bill bill) {
        return jdbcOperations.update(UPDATE_ONE_BILL,
                                    bill.getType(),
                                    bill.getMoney(),
                                    bill.getFolder(),
                                    bill.getRemark(),
                                    bill.getBillDate(),
                                    bill.getId());
    }

    @Override
    public int addOneFolder(BillFolder billFolder) {
        return jdbcOperations.update(INSERT_ONE_FOLDER,billFolder.getUid(),
                                                billFolder.getName(),new Date());
    }

    @Override
    public int addSomeFolders(List<BillFolder> billFolders) {
        return 0;
    }

    @Override
    public int deleteByFolder(List<BillFolder> billFolderList) {
        int count = 0;

        for (int i = 0 ; i < billFolderList.size(); i ++){
            count += jdbcOperations.update(DELETE_BY_FOLDER,
                                            billFolderList.get(i).getUid(),
                                            billFolderList.get(i).getName());
        }

        return count;
    }

    @Override
    public int updateFolder(BillFolder oldBillFolder, BillFolder newBillFolder) {
        return jdbcOperations.update(UPDATE_FOLDER_NAME,
                                    newBillFolder.getName(),
                                    oldBillFolder.getUid(),
                                    oldBillFolder.getName()
                                    );
    }

    @Override
    public List<BillFolder> getAllFolder(int uid) {
        return jdbcOperations.query(FIND_ALL_FOLDER,new BillFolderRowMapper(),uid);
    }

    private class BillFolderRowMapper implements  RowMapper<BillFolder>{
        @Override
        public BillFolder mapRow(ResultSet rs, int rowNum) throws SQLException {
            BillFolder bf = new BillFolder();
            bf.setUid(rs.getInt("uid"));
            bf.setName(rs.getString("folder"));
            return bf;
        }
    }

    private class BillRowMapper implements RowMapper<Bill>{

        @Override
        public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bill b = new Bill();
            b.setId(rs.getInt("id"));
            b.setUid(rs.getInt("uid"));
            b.setType(rs.getString("type"));
            b.setMoney(rs.getDouble("money"));
            b.setFolder(rs.getString("folder"));
            b.setRemark(rs.getString("remark"));
            b.setBillDate(rs.getString("bill_date"));
            b.setInClouded(true);               //
            return b;
        }
    }
}
