package cupraccoon.myboard.controller;

import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.web.SessionConst;
import cupraccoon.myboard.web.argumentresolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(@Login Member loginMember, Model model){
        log.info("home controller");
        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        return "home";

    }
}
