package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long> {

    @Override
    @EntityGraph(attributePaths = {"writeMEmber"})
    List<Board> findAll();

    @EntityGraph(attributePaths = {"writeMember"})
    Page<Board> findByDtype(String dtype,Pageable pageable);

    @EntityGraph(attributePaths = {"writeMember"})
    Page<Board> findByRecommendGreaterThanEqual(int recommend, Pageable pageable);

}
