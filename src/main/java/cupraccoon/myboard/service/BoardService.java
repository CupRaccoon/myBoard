package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> findBoards() {
        return boardRepository.findAll();

    }
}