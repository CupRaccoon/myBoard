package cupraccoon.myboard.controller.board;

import cupraccoon.myboard.controller.PasswordForm;
import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.domain.board.Category;
import cupraccoon.myboard.service.BoardService;
import cupraccoon.myboard.web.SessionConst;
import cupraccoon.myboard.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


//    @GetMapping("/test")
//    public String pageList(Model model){
//        return "/board/list";
//    }

    @GetMapping("/all")
    public String list(@Login Member loginMember, Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable) {
        Page<Board> boards = boardService.findAllPosts(pageable);

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        model.addAttribute("boardType", "all");
        model.addAttribute("boards", boards);
        model.addAttribute("korName", "종합게시판");

        return "/board/list";
    }

    @GetMapping("/best")
    public String bestList(@Login Member loginMember, Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                                   Pageable pageable) {
        int recommend = 5;
        Page<Board> boards = boardService.findPostsByRecommend(5, pageable);

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        model.addAttribute("boardType", "best");
        model.addAttribute("boards", boards);
        model.addAttribute("korName", "베스트게시판");

        return "/board/list";
    }


    @GetMapping("/{boardType}")
    public String list(@Login Member loginMember, @PathVariable String boardType, Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                               Pageable pageable) {
        String category = Category.findCategoryByUrl(boardType);
        Page<Board> boards = boardService.findPostsByCategory(category, pageable);

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        model.addAttribute("boards", boards);
        model.addAttribute("boardType", boardType);
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("korName", korName);

        return "/board/list";
    }

    @GetMapping("/{boardType}/new")
    public String newBoard(@Login Member loginMember, @PathVariable String boardType, Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        model.addAttribute("boardType", boardType);
        model.addAttribute("boardDto", new BoardDto());
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("korName", korName);

        return "/board/writeBoardForm";
    }

    @PostMapping("/{boardType}/new")
    public String newBoard(@Login Member loginMember, @PathVariable String boardType,
                           BoardDto boardDto, Model model) throws Exception {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        model.addAttribute("boardType", boardType);
        String dtype = Category.findCategoryByUrl(boardType);
        Board board = Board.createUnsigned(dtype, boardDto.getTitle(),
                boardDto.getContent(), boardDto.getUserName(), boardDto.getPassword());
        boardService.saveBoard(board);
        Long savedId = board.getId();

        return "redirect:/board/" + boardType + "/" + savedId;
    }

    @GetMapping("/{boardType}/{boardId}")
    public String boardContent(@Login Member loginMember,
                               @PathVariable String boardType, @PathVariable Long boardId, Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        Board findBoard = boardService.findOne(boardId);
        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("board", findBoard);
        model.addAttribute("korName", korName);

        return "/board/content";
    }

    @GetMapping("/{boardType}/{boardId}/edit")
    public String passwordFormToUpdate(@Login Member loginMember, @PathVariable String boardType,
                                       @PathVariable Long boardId, Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("validateType", "edit");
        model.addAttribute("korName", korName);
        model.addAttribute("boardId", boardId);
        model.addAttribute("passwordForm", new PasswordForm());

        return "/board/passwordForm";

    }

    @PostMapping("/{boardType}/{boardId}/edit/validate")
    public String updateBoardForm(@Login Member loginMember,
                                  @PathVariable String boardType, @PathVariable Long boardId,
                                  @ModelAttribute("password") PasswordForm passwordForm, Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        String password = passwordForm.getPassword();
        Board board = boardService.findOne(boardId);

        if (boardService.validatePassword(boardId, password)) {
            String korName = Category.findKorNameByUrl(boardType);
            BoardDto boardDto = new BoardDto();
            boardDto.setUserName(board.getUnsignedUser());
            boardDto.setPassword(board.getUnsignedPassword());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("korName", korName);
            model.addAttribute("boardId", boardId);
            return "/board/editBoardForm";
        } else {
            log.error("wrong password");
            log.error("password : " + password);
            model.addAttribute("errorMessage", password);
            return "/board/error";
        }



    }

    @PostMapping("/{boardType}/{boardId}/edit")
    public String updateBoardForm(@Login Member loginMember,
                                  @PathVariable String boardType, @PathVariable Long boardId,
                                  @ModelAttribute("boardDto") BoardDto boardDto, Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        Board board = boardService.findOne(boardId);
        String korName = Category.findKorNameByUrl(boardType);
        board.setUnsignedUser(boardDto.getUserName());
        board.setUnsignedPassword(boardDto.getPassword());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getTitle());
        boardService.saveBoard(board);
        model.addAttribute("korName", korName);
        model.addAttribute("boardId", boardId);

        return "redirect:/board/" + boardType + "/" + boardId;

    }

    @GetMapping("/{boardType}/{boardId}/delete")
    public String passwordFormToDelete(@Login Member loginMember,
                                       @PathVariable String boardType, @PathVariable Long boardId,
                                       Model model) {
        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        String korName = Category.findKorNameByUrl(boardType);
        model.addAttribute("validateType", "delete");
        model.addAttribute("korName", korName);
        model.addAttribute("boardId", boardId);
        model.addAttribute("passwordForm", new PasswordForm());

        return "/board/passwordForm";
    }

    @PostMapping("/{boardType}/{boardId}/delete/validate")
    public String deleteBoard(@Login Member loginMember,
                              @PathVariable String boardType, @PathVariable Long boardId,
                              @ModelAttribute("password") PasswordForm passwordForm, Model model) {
        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        String password = passwordForm.getPassword();

        if (boardService.validatePassword(boardId, password)) {
            boardService.deleteById(boardId);
            String korName = Category.findKorNameByUrl(boardType);
            return "redirect:/board/" + boardType;
        } else {
            log.error("wrong password");
            log.error("input_password : " + password);
            model.addAttribute("errorMessage", password);
            return "/board/error";
        }
    }

    @GetMapping("{boardType}/{boardId}/recommend")
    public String increaseBoardRecommend(@Login Member loginMember,
                                         @PathVariable String boardType, @PathVariable Long boardId,
                                         Model model) {
        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        boardService.increaseRecommend(boardId);

        return "redirect:/board/" + boardType + "/" + boardId;
    }
}
