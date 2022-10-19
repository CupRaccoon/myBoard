package cupraccoon.myboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/dev")
    public String devBoard(){

        return "/index";
    }
    @GetMapping("/free")
    public String freeBoard(){
        return "/index";
    }
    @GetMapping("/game")
    public String gameBoard(){
        return "/index";
    }
    @GetMapping("/hobby")
    public String hobbyBoard(){
        return "/index";
    }

}
