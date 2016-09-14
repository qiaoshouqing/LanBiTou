package top.glimpse.lanbitou.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glimpse.lanbitou.data.BillRepository;
import top.glimpse.lanbitou.domain.Bill;
import top.glimpse.lanbitou.domain.BillFolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Henvealf on 16-5-14.
 */

@Controller
@RequestMapping(value = "/bill")
public class BillController {

    private static final int SUCCESS = 200;

    private BillRepository billRepository;

    @Autowired
    public BillController(BillRepository billRepository){
        this.billRepository = billRepository;
    }

    @RequestMapping(value="/addOne", produces = "application/json;charset=UTF-8", method = POST )
    @ResponseBody
    public int addOne(@RequestBody Bill bill){

        SimpleDateFormat format = new SimpleDateFormat();
        if(bill.getBillDate() == null){
            bill.setBillDate(format.format(new Date()));
        }

        return billRepository.addOne(bill);
    }

    @RequestMapping(value="/addSome", produces = "application/json;charset=UTF-8", method = POST )
    @ResponseBody
    public int addSome(@RequestBody List<Bill> billList){
        int count = 0;
        for(Bill bill : billList){
            count += billRepository.addOne(bill);
        }
        return count;
    }


    @RequestMapping(value="/getOneById/{id}",produces = "application/json;charset=UTF-8",method = GET )
    @ResponseBody
    public Bill getOneById(@PathVariable int id){
        return billRepository.getOneById(id);
    }

    @RequestMapping(value="/getSomeByFolder/{uid}/{folderName}",produces = "application/json;charset=UTF-8",method = GET )
    @ResponseBody
    public List<Bill> getSomeByFolder(@PathVariable int uid,
                                      @PathVariable String folderName){
        return billRepository.getSomeByFolder(uid,folderName);
    }

    @RequestMapping(value="/getSomeByUid/{uid}",produces = "application/json;charset=UTF-8",method = GET )
    @ResponseBody
    public List<Bill> getAllByUid(@PathVariable int uid){
        return billRepository.getAllByUid(uid);
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/deleteById/{id}",produces = "application/json;charset=UTF-8",method = GET )
    @ResponseBody
    public int deleteById(@PathVariable int id){
        return billRepository.deleteById(id);
    }

    @RequestMapping(value="/deleteByFolder",produces = "application/json;charset=UTF-8",method = POST )
    @ResponseBody
    public int deleteByFolder(@RequestBody List<BillFolder> billFolders){
        return billRepository.deleteByFolder(billFolders);
    }



    @RequestMapping(value="/deleteSome", produces = "application/json;charset=UTF-8", method = POST )
    @ResponseBody
    public int deleteSome(@RequestBody List<Bill> billList){
        int count = 0;
        for(Bill b : billList){
            count += billRepository.deleteById(b.getId());
        }
        return count;
    }

    @RequestMapping(value="/addOneFolder",produces = "application/json;charset=UTF-8",method = POST )
    @ResponseBody
    public int addOneFolder(@RequestBody BillFolder billFolder){
        return billRepository.addOneFolder(billFolder);
    }

    @RequestMapping(value="/addSomeFolder",produces = "application/json;charset=UTF-8",method = POST )
    @ResponseBody
    public int addSomeFolder(@RequestBody List<BillFolder> billFolder){
        int count = 0;
        for(BillFolder bf : billFolder){
            count += billRepository.addOneFolder(bf);
        }
        return count;
    }

    @RequestMapping(value="/findAllFolder/{uid}",produces = "application/json;charset=UTF-8",method = GET )
    @ResponseBody
    public List<BillFolder> findAllFolder(@PathVariable int uid){
        return billRepository.getAllFolder(uid);
    }


    @RequestMapping(value="/updateOneBill",produces = "application/json;charset=UTF-8",method = POST )
    @ResponseBody
    public int updateOneBill(@RequestBody Bill bill){
        return billRepository.update(bill);
    }

    @RequestMapping(value="/updateSomeBill",produces = "application/json;charset=UTF-8",method = POST )
    @ResponseBody
    public int updateSomeBill(@RequestBody List<Bill> bills){
        int count = 0;
        for(Bill b : bills){
            count += billRepository.update(b);
        }
        return count;
    }

    @RequestMapping(value = "/updateOneFolder", produces = "application/json;charset=UTF-8", method = POST)
    @ResponseBody
    public int updateFolder(@RequestBody List<BillFolder> billFolderList){

        int count = 0;
        BillFolder oldBillFolder = null;
        BillFolder newBillFolder;
        for(int i= 0; i < billFolderList.size(); i++){
            if(i % 2 == 0){             //偶数 0 ,2 ,4 为旧
                oldBillFolder = billFolderList.get(i);
            } else {                    //奇数 1, 3, 5 为新
                newBillFolder = billFolderList.get(i);
                count += billRepository.updateFolder(oldBillFolder,newBillFolder);
            }
        }
        return count;
    }


}
