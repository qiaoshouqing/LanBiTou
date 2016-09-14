package top.glimpse.lanbitou.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glimpse.lanbitou.data.NoteBookRepository;
import top.glimpse.lanbitou.domain.Note;
import top.glimpse.lanbitou.domain.NoteBook;

import java.util.List;

/**
 * Created by joyce on 16-6-12.
 */
@Controller
@RequestMapping(value="/notebook")
public class NoteBookController {

    private NoteBookRepository noteBookRepository;

    @Autowired
    public NoteBookController(NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }

    @RequestMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<NoteBook> getAll() {
        return noteBookRepository.getAll();
    }

    @RequestMapping(value = "/postOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String postOne(@RequestBody NoteBook noteBook) {
        String bid_fid = noteBookRepository.postOne(noteBook);
        return "postOne" + bid_fid;
    }

    @RequestMapping(value = "/postAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String postAll(@RequestBody List<NoteBook> noteBookList) {
        String result = noteBookRepository.postAll(noteBookList);
        return "postAll" + result;
    }



    @RequestMapping(value = "/deleteOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOne(@RequestBody NoteBook noteBook) {
        noteBookRepository.deleteOne(noteBook);
        return "deleteOne";
    }

    @RequestMapping(value = "/deleteAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAll(@RequestBody List<NoteBook> noteBookList) {
        noteBookRepository.deleteAll(noteBookList);
        return "deleteAll";
    }

    @RequestMapping(value = "/updateOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateOne(@RequestBody NoteBook notebook) {
        noteBookRepository.updateOne(notebook);
        return "updateOne";
    }

    @RequestMapping(value = "/updateAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateAll(@RequestBody List<NoteBook> noteBookList) {
        noteBookRepository.updateAll(noteBookList);
        return "updateAll";
    }




}
