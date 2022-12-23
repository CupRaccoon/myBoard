package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.board.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository_old boardRepository;

    @Test
    @Transactional
    public void save() throws Exception {
        Board board = new Board("제목","내용");

        Long savedId = boardRepository.save(board);

        Board findBoard = boardRepository.findOne(savedId);

        Assertions.assertThat(findBoard.getId()).isEqualTo(board.getId());
        Assertions.assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
        Assertions.assertThat(findBoard.getContent()).isEqualTo(board.getContent());
    }


    @Test
    void findOne() {
    }
}