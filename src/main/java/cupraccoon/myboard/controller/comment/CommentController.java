package cupraccoon.myboard.controller.comment;

import cupraccoon.myboard.controller.board.BoardPassword;
import cupraccoon.myboard.controller.board.SignedNewBoardRequest;
import cupraccoon.myboard.controller.board.UnsignedNewBoardRequest;
import cupraccoon.myboard.domain.Comment;
import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.domain.board.Category;
import cupraccoon.myboard.service.BoardService;
import cupraccoon.myboard.service.CommentService;
import cupraccoon.myboard.web.SessionConst;
import cupraccoon.myboard.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    @PostMapping("/{boardType}/{boardId}/new")
    public String newComment(@Login Member loginMember,
                             @PathVariable String boardType,@PathVariable Long boardId,
                             @ModelAttribute("newCommentRequest") NewCommentRequest newCommentRequest,
                             Model model) {

        model.addAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        model.addAttribute("boardType", boardType);
        String korName = Category.findKorNameByUrl(boardType);
        Board findBoard = boardService.findOne(boardId);
        Comment comment = Comment.builder().content(newCommentRequest.getContent()).
        unsignedMember(newCommentRequest.getUserName()).board(findBoard).
                unsignedPassword(newCommentRequest.getPassword()).recommend(0).writeMember(null).build();
        commentService.saveComment(comment);
        model.addAttribute("korName", korName);
        return "redirect:/board/" + boardType + "/" + boardId;

    }
}
