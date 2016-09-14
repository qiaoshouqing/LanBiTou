package top.glimpse.lanbitou.data;

import top.glimpse.lanbitou.domain.User;

/**
 * Created by joyce on 16-5-11.
 */
public interface UserRepository {
    User login(User user);
    User signup(User user);
}
