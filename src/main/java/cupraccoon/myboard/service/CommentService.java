package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.Comment;
import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.repository.BoardRepository;
import cupraccoon.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    @Transactional
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public List<Comment> findComments(Board board){
        return commentRepository.findByBoard(board);
    }

}
