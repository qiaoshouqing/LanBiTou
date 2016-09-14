package top.glimpse.lanbitou.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.glimpse.lanbitou.data.NoteRepository;
import top.glimpse.lanbitou.data.UserRepository;
import top.glimpse.lanbitou.domain.User;

/**
 * Created by joyce on 16-5-23.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public User login(@RequestBody User user) {
        User result = userRepository.login(user);
        if (result.getEmail().equals(user.getEmail()) && result.getPassword().equals(user.getPassword()) ) {
            return result;
        }
        else {
            return null;
        }
    }


    @RequestMapping(value = "/signup", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public User signup(@RequestBody User user) {
        return userRepository.signup(user);
    }




}
