package top.glimpse.lanbitou.data;

import top.glimpse.lanbitou.domain.Note;

import java.util.List;

/**
 * Created by joyce on 16-5-11.
 */
public interface NoteRepository {
    Note get(int id);
    List<Note> getAll();
    List<Note> getSome(int bid);
    int postOne(Note note);
    void updateOne(Note note);
    void updateAll(List<Note> notelist);
    void deleteAll(List<Note> notelist);
    void postAll(List<Note> notelist);
    void deleteOne(Note note);
}