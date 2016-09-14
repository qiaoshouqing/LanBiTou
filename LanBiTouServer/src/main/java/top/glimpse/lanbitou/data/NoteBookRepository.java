package top.glimpse.lanbitou.data;

import top.glimpse.lanbitou.domain.NoteBook;

import java.util.List;

/**
 * Created by joyce on 16-6-8.
 */
public interface NoteBookRepository {
    List<NoteBook> getAll();
    List<NoteBook> getChildrenNoteBooks(int bid);
    NoteBook getNoteBook(int bid);
    String postOne(NoteBook noteBook);
    void deleteOne(NoteBook noteBook);
    void updateOne(NoteBook noteBook);
    void updateAll(List<NoteBook> noteBookList);
    void deleteAll(List<NoteBook> noteBookList);
    String postAll(List<NoteBook> noteBookList);


}
