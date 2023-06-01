package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.Comment;
import cupraccoon.myboard.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBoard(Board board);
}
