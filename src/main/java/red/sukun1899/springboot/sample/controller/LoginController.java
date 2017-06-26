package red.sukun1899.springboot.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author su-kun1899
 */
@Controller
public class LoginController {
    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }
}
