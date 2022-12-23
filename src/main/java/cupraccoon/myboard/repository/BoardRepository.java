package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findByDtype(String dtype,Pageable pageable);

    Page<Board> findByRecommendGreaterThanEqual(int recommend, Pageable pageable);

}
