package rashjz.info.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/stomp")
    public String stomp( ) {
        return "stomp";
    }

    @GetMapping("/test")
    public String index( ) {
        return "index";
    }
}
