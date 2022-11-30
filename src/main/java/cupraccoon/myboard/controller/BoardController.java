package cupraccoon.myboard.controller;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.domain.board.Category;
import cupraccoon.myboard.domain.board.Dev;
import cupraccoon.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping()
    public String list(Model model){
        List<Board> boards = boardService.findAllPosts();
        model.addAttribute("boards",boards);
        return "/board/list";
    }

    @GetMapping("/{boardType}")
    public String list(@PathVariable String boardType, Model model){
        String category = Category.findCategoryByUrl(boardType);
        List<Board> boards = boardService.findPostsByCategory(category);
        model.addAttribute("boards",boards);
        model.addAttribute("boardtype",boardType);
        return "/board/list";
    }
    @GetMapping("/{boardType}/new")
    public String newBoard(@PathVariable String boardType, Model model){
        model.addAttribute("boardType",boardType);
        model.addAttribute("boardDto",new BoardDto());
        return "/board/write";
    }
    @PostMapping("/{boardType}/new")
    public String newBoard(@PathVariable String boardType, BoardDto boardDto,Model model) throws Exception {
        model.addAttribute("boardType",boardType);
        Board board =  Board.createUnsigned(boardDto.getTitle(),
                boardDto.getContent(), boardDto.getNickName(), boardDto.getPassword());
        boardService.saveBoard(board);
        Long savedId = board.getId();
        return "redirect:/board/"+boardType+"/"+savedId;
    }

    @GetMapping("/{boardType}/{boardId}")
    public String boardCotent(@PathVariable String boardType, @PathVariable Long boardId,Model model){
        Board findBoard = boardService.findOne(boardId);
        model.addAttribute(findBoard);
        return "/board/content";
    }

}
