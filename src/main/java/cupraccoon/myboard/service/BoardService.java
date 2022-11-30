package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }

    public List<Board> findAllPosts() {
        return boardRepository.findAll();
    }
    public List<Board> findPostsByCategory(String board){
        return boardRepository.findByCategory(board);
    }
}
