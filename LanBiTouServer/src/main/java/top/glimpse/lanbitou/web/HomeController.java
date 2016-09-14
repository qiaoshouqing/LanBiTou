package top.glimpse.lanbitou.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by joyce on 16-5-11.
 */
@Controller
public class HomeController {

    @RequestMapping(value="/", method = GET)
    public String home() {
        return "home";
    }




}
