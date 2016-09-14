package top.glimpse.lanbitou.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import top.glimpse.lanbitou.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Henvealf on 16-5-14.
 */
@Repository
public class UserJdbcRepository implements UserRepository{

    private static final String SELECT_USER = "select * from user where email = ? and password = ?";
    private static final String INSERT_USER = "insert into user(name, password, email, created_at) values (?, ?, ?, ?)";


    private JdbcOperations jdbcOperations;

    @Autowired
    public UserJdbcRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public User login(User user) {
        return jdbcOperations.queryForObject(
                SELECT_USER,
                new UserRowMapper(),
                user.getEmail(), user.getPassword());
    }

    @Override
    public User signup(User user) {
        jdbcOperations.update(INSERT_USER,
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                new Timestamp(System.currentTimeMillis()).toString());
        
        return jdbcOperations.queryForObject(
                SELECT_USER,
                new UserRowMapper(),
                user.getEmail(), user.getPassword());
    }

    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("uid"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getString("avatar"),
                    rs.getString("email"),
                    rs.getString("created_at"));
        }
    }
}
