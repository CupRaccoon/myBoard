package cupraccoon.myboard.controller;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/{boardType}")
    public String bestBoard(@PathVariable String boardType, Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards",boards);
        model.addAttribute("boardtype",boardType);
        return "/board/list";
    }
//    @GetMapping("/total")
//    public String totalBoard(Model model){
//
//        return "/board/list";
//    }
//    @GetMapping("/dev")
//    public String devBoard(Model model){
//        return  "/board/list";
//    }
//    @GetMapping("/free")
//    public String freeBoard(Model model) {
//        return  "/board/list";
//    }
//    @GetMapping("/game")
//    public String gameBoard(Model model){
//        return  "/board/list";
//    }
//    @GetMapping("/hobby")
//    public String hobbyBoard(Model model){
//        return  "/board/list";
//    }

}
