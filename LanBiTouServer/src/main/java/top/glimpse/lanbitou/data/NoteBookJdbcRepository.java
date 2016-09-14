package top.glimpse.lanbitou.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import top.glimpse.lanbitou.domain.Note;
import top.glimpse.lanbitou.domain.NoteBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joyce on 16-6-8.
 */
@Controller
public class NoteBookJdbcRepository implements NoteBookRepository {

    private static final String SELECT_CHILDREN_BY_ID = "select * from notebook where fid = ?";
    private static final String SELECT_ALL = "select * from notebook";
    private static final String SELECT_BY_ID = "select * from notebook where bid = ?";
    private static final String INSERT_NOTEBOOK = "insert into notebook(uid, name, fid) values(?, ?, ?)";
    private static final String DELETE_NOTEBOOK = "delete from notebook where bid = ?";
    private static final String UPDATE_NOTEBOOK = "update notebook set name = ?, fid = ? where bid = ?";
    private static final String SELECT_NOTEBOOK_NEWEST_BID = "select bid from notebook order by bid desc limit 1";
    private static final String SELECT_NOTEBOOK_NEWEST_FID = "select fid from notebook order by bid desc limit 1";




    private JdbcOperations jdbcOperations;

    @Autowired
    public NoteBookJdbcRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<NoteBook> getAll() {
        return jdbcOperations.query(
                SELECT_ALL,
                new NoteRowMapper());
    }

    @Override
    public List<NoteBook> getChildrenNoteBooks(int bid) {

        return jdbcOperations.query(
                SELECT_CHILDREN_BY_ID,
                new NoteRowMapper(), bid);
    }

    @Override
    public NoteBook getNoteBook(int bid) {
        try {
            return jdbcOperations.queryForObject(
                    SELECT_BY_ID,
                    new NoteRowMapper(), bid);
        } catch (org.springframework.dao.EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public String postOne(NoteBook noteBook) {
        jdbcOperations.update(INSERT_NOTEBOOK,
                noteBook.getUid(),
                noteBook.getName(),
                noteBook.getFid());

        int bid = jdbcOperations.queryForObject(
                SELECT_NOTEBOOK_NEWEST_BID,
                Integer.class);

        int fid = jdbcOperations.queryForObject(
                SELECT_NOTEBOOK_NEWEST_FID,
                Integer.class);

        return "@" + bid + "#" + fid;
    }

    @Override
    public String postAll(List<NoteBook> noteBookList) {
        String noteBookStr = "";
        for(NoteBook noteBook : noteBookList) {
            noteBookStr += postOne(noteBook);
        }
        return noteBookStr;
    }

    @Override
    public void deleteOne(NoteBook notebook) {
        jdbcOperations.update(DELETE_NOTEBOOK,
                notebook.getBid());
    }

    @Override
    public void updateOne(final NoteBook notebook) {
        jdbcOperations.update(UPDATE_NOTEBOOK,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setString(1, notebook.getName());
                        ps.setInt(2, notebook.getFid());
                        ps.setInt(3, notebook.getBid());
                    }
                });
    }

    @Override
    public void updateAll(List<NoteBook> noteBookList) {
        for(NoteBook noteBook : noteBookList) {
            updateOne(noteBook);
        }
    }

    @Override
    public void deleteAll(List<NoteBook> noteBookList) {
        for(NoteBook noteBook : noteBookList) {
            deleteOne(noteBook);
        }
    }

    private static class NoteRowMapper implements RowMapper<NoteBook> {
        public NoteBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new NoteBook(
                    rs.getInt("bid"),
                    rs.getInt("uid"),
                    rs.getString("name"),
                    rs.getInt("fid")
                    );
        }
    }
}
