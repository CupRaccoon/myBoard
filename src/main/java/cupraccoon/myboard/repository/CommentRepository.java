package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.Comment;
import cupraccoon.myboard.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
