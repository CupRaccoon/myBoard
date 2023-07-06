package cupraccoon.myboard.service;

import cupraccoon.myboard.controller.comment.CommentResponse;
import cupraccoon.myboard.domain.Comment;
import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.repository.BoardRepository;
import cupraccoon.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findComments(Board board) {
        return commentRepository.findByBoardOrderByWriteDateAsc(board);
    }

    public Comment findOne(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(null);
    }

    public List<CommentResponse> findAllCommentsByBoard(Board board) {
        List<Comment> commentList = commentRepository.findByBoardOrderByWriteDateAsc(board);
        List<CommentNode> commentTree = new ArrayList<>();
        Map<Long, CommentNode> commentMap = new HashMap<>();
        commentList.forEach(c -> {
            CommentNode commentNode = new CommentNode(0, c.getId(), c, new ArrayList<>());
            commentMap.put(commentNode.getCommentId(), commentNode);
            if (c.getParentComment() != null) {
                CommentNode parentNode = commentMap.get(c.getParentComment().getId());
                parentNode.getChildComments().add(commentNode);
                commentNode.setDepth(parentNode.getDepth() + 1);
            } else {
                commentTree.add(commentNode);
            }
        });
        return ConvertTreeToResponse(commentTree);
    }

    public List<CommentResponse> ConvertTreeToResponse(List<CommentNode> commentTree) {
        List<CommentResponse> result = new ArrayList<>();
        Stack<CommentNode> stack = new Stack<>();

        for (int i = commentTree.size() - 1; i >= 0; i--) {
            stack.push(commentTree.get(i));
        }
        while (!stack.empty()) {
            CommentNode commentNode = stack.pop();
            result.add(new CommentResponse(commentNode.getComment(), commentNode.getDepth()));
            List<CommentNode> childComments = commentNode.getChildComments();
            for (int i = childComments.size() - 1; i >= 0; i--) {
                stack.push(childComments.get(i));

            }
        }
        return result;
    }
}
