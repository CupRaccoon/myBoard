package cupraccoon.myboard.controller;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.domain.board.Category;
import cupraccoon.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/all")
    public String list(Model model) {
        List<Board> boards = boardService.findAllPosts();
        model.addAttribute("boardType","all");
        model.addAttribute("boards", boards);
        model.addAttribute("korName","종합게시판");
        return "/board/list";
    }
    @GetMapping("/best")
    public String bestList(Model model) {
        int recommend = 5;
        List<Board> boards = boardService.findPostsByRecommend(5);
        model.addAttribute("boardType","best");
        model.addAttribute("boards", boards);
        model.addAttribute("korName","베스트게시판");
        return "/board/list";
    }
    @GetMapping("/{boardType}")
    public String list(@PathVariable String boardType, Model model) {
        String category = Category.findCategoryByUrl(boardType);
        List<Board> boards = boardService.findPostsByCategory(category);
        model.addAttribute("boards", boards);
        model.addAttribute("boardtype", boardType);
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("korName", korName);


        return "/board/list";
    }

    @GetMapping("/{boardType}/new")
    public String newBoard(@PathVariable String boardType, Model model) {
        model.addAttribute("boardType", boardType);
        model.addAttribute("boardDto", new BoardDto());
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("korName", korName);
        return "/board/writeBoardForm";
    }

    @PostMapping("/{boardType}/new")
    public String newBoard(@PathVariable String boardType, BoardDto boardDto, Model model) throws Exception {
        model.addAttribute("boardType", boardType);
        String dtype = Category.findCategoryByUrl(boardType);
        Board board = Board.createUnsigned(dtype, boardDto.getTitle(),
                boardDto.getContent(), boardDto.getUserName(), boardDto.getPassword());
        boardService.saveBoard(board);
        Long savedId = board.getId();
        return "redirect:/board/" + boardType + "/" + savedId;
    }

    @GetMapping("/{boardType}/{boardId}")
    public String boardCotent(@PathVariable String boardType, @PathVariable Long boardId, Model model) {
        Board findBoard = boardService.findOne(boardId);
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("board", findBoard);
        model.addAttribute("korName", korName);
        return "/board/content";
    }

    @GetMapping("/{boardType}/{boardId}/edit")
    public String passwordFormToUpdate(@PathVariable String boardType,
                                  @PathVariable Long boardId, Model model){
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("validateType","edit");
        model.addAttribute("korName", korName);
        model.addAttribute("boardId",boardId);
        model.addAttribute("passwordForm",new PasswordForm());
        return "/board/passwordForm";

    }
    @PostMapping("/{boardType}/{boardId}/edit/validate")
    public String updateBoardForm(@PathVariable String boardType, @PathVariable Long boardId,
                                  @ModelAttribute("password") PasswordForm passwordForm, Model model){
        String password =  passwordForm.getPassword();
        Board board = boardService.findOne(boardId);
        if(boardService.validatePassword(boardId,password)){
            String korName = Category.findKorNameByUrl(boardType);
            BoardDto boardDto = new BoardDto();
            boardDto.setUserName(board.getUnsignedUser());
            boardDto.setPassword(board.getUnsignedPassword());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            model.addAttribute("boardDto",boardDto);
            model.addAttribute("korName", korName);
            model.addAttribute("boardId",boardId);
            return "/board/editBoardForm";
        }
        else{
            log.error("wrong password");
            log.error("password : ",password);
            model.addAttribute("errorMessage",password);
            return "/board/error";
        }
    }
    @PostMapping("/{boardType}/{boardId}/edit")
    public String updateBoardForm(@PathVariable String boardType, @PathVariable Long boardId,
                                  @ModelAttribute("boardDto") BoardDto boardDto, Model model){
        Board board = boardService.findOne(boardId);
        String korName = Category.findKorNameByUrl(boardType);
        board.setUnsignedUser(boardDto.getUserName());
        board.setUnsignedPassword(boardDto.getPassword());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getTitle());
        boardService.saveBoard(board);
        model.addAttribute("korName", korName);
        model.addAttribute("boardId",boardId);
        return "redirect:/board/" + boardType + "/" + boardId;

    }

    @GetMapping("/{boardType}/{boardId}/delete")
    public String passwordFormToDelete(@PathVariable String boardType,
                                       @PathVariable Long boardId, Model model){
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("validateType","delete");
        model.addAttribute("korName", korName);
        model.addAttribute("boardId",boardId);
        model.addAttribute("passwordForm",new PasswordForm());
        return "/board/passwordForm";
    }
    @PostMapping("/{boardType}/{boardId}/delete/validate")
    public String deleteBoard(@PathVariable String boardType, @PathVariable Long boardId,
                                  @ModelAttribute("password") PasswordForm passwordForm, Model model){
        String password =  passwordForm.getPassword();
        if(boardService.validatePassword(boardId,password)){
            boardService.deleteById(boardId);
            String korName = Category.findKorNameByUrl(boardType);
            return "redirect:/board/" + boardType;
        }
        else{
            log.error("wrong password");
            log.error("input_password : "+password);
            model.addAttribute("errorMessage",password);
            return "/board/error";
        }
    }
    @GetMapping("{boardType}/{boardId}/recommend")
    public String increaseBoardRecommend(@PathVariable String boardType, @PathVariable Long boardId,
                                         Model model){
        boardService.increaseRecommend(boardId);
        return "redirect:/board/" + boardType + "/" + boardId;
    }
}
