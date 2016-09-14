package top.glimpse.lanbitou.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import top.glimpse.lanbitou.data.NoteRepository;
import top.glimpse.lanbitou.domain.Note;


import java.util.List;

/**
 * Created by joyce on 16-5-11.
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping(value = "/getOne/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Note get(@PathVariable int id) {
        return noteRepository.get(id);
    }

    @RequestMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Note> getAll() {
        return noteRepository.getAll();
    }

    @RequestMapping(value = "/getSome/{bid}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Note> getSome(@PathVariable int bid) {
        return noteRepository.getSome(bid);
    }

    @RequestMapping(value = "/postOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String postOne(@RequestBody Note note) {
        int nid = noteRepository.postOne(note);
        return "postOne" + nid;
    }

    @RequestMapping(value = "/postAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String postAll(@RequestBody List<Note> noteList) {
        noteRepository.postAll(noteList);
        return "postAll";
    }



    @RequestMapping(value = "/updateOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateOne(@RequestBody Note note) {
        noteRepository.updateOne(note);
        return "updateOne";
    }

    @RequestMapping(value = "/updateAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateAll(@RequestBody List<Note> noteList) {
        noteRepository.updateAll(noteList);
        return "updateAll";
    }

    @RequestMapping(value = "/deleteOne", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOne(@RequestBody Note note) {
        noteRepository.deleteOne(note);
        return "deleteOne";
    }

    @RequestMapping(value = "/deleteAll", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAll(@RequestBody List<Note> noteList) {
        noteRepository.deleteAll(noteList);
        return "deleteAll";
    }


}