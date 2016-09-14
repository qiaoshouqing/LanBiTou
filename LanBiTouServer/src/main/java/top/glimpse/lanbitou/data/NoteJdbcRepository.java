package top.glimpse.lanbitou.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import top.glimpse.lanbitou.domain.Note;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by joyce on 16-5-11.
 */
@Repository
public class NoteJdbcRepository implements NoteRepository{

    private static final String SELECT_NOTE_BY_ID = "select * from note where nid = ?";
    private static final String SELECT_NOTE = "select * from note";
    private static final String SELECT_NOTE_NEWEST_ID = "select nid from note order by nid desc limit 1";
    private static final String SELECT_SOME_NOTE = "select * from note where bid = ?";
    private static final String INSERT_NOTE = "insert into note(uid, bid, title, content, mark, created_at) values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_NOTE = "update note set bid = ?, title = ?, content = ?, mark = ? where nid = ?";
    private static final String DELETE_NOTE = "delete from note where nid = ?";

    private JdbcOperations jdbcOperations;

    @Autowired
    public NoteJdbcRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Note get(int id) {
        return jdbcOperations.queryForObject(
                SELECT_NOTE_BY_ID,
                new NoteRowMapper(), id);
    }

    @Override
    public List<Note> getAll() {
        return jdbcOperations.query(
                SELECT_NOTE,
                new NoteRowMapper());
    }

    @Override
    public List<Note> getSome(int bid) {
        return jdbcOperations.query(
                SELECT_SOME_NOTE,
                new NoteRowMapper(), bid);
    }

    @Override
    public int postOne(Note note) {
         jdbcOperations.update(INSERT_NOTE,
                note.getUid(),
                note.getBid(),
                note.getTitle(),
                note.getContent(),
                note.getMark(),
                new Timestamp(System.currentTimeMillis()).toString());


        return jdbcOperations.queryForObject(
                SELECT_NOTE_NEWEST_ID,
                Integer.class);

    }

    @Override
    public void postAll(List<Note> notelist) {
        for (Note note : notelist) {
            postOne(note);
        }
    }


    @Override
    public void updateOne(final Note note) {
        jdbcOperations.update(UPDATE_NOTE,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, note.getBid());
                        ps.setString(2, note.getTitle());
                        ps.setString(3, note.getContent());
                        ps.setBoolean(4, note.getMark());
                        ps.setInt(5, note.getNid());
                    }
                });
    }

    @Override
    public void updateAll(List<Note> notelist) {
        for(Note note : notelist) {
            updateOne(note);
        }
    }

    @Override
    public void deleteOne(Note note) {
        jdbcOperations.update(DELETE_NOTE,
                note.getNid());
    }

    @Override
    public void deleteAll(List<Note> notelist) {
        for (Note note : notelist) {
            deleteOne(note);
        }
    }

    private static class NoteRowMapper implements RowMapper<Note> {
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Note(
                    rs.getInt("nid"),
                    rs.getInt("uid"),
                    rs.getInt("bid"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getBoolean("mark"),
                    rs.getString("created_at"));
        }
    }
}