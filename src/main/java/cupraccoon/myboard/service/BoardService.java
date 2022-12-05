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
    @Transactional
    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }

    public List<Board> findAllPosts() {
        return boardRepository.findAll();
    }
    public List<Board> findPostsByCategory(String dtype){
        return boardRepository.findByCategory(dtype);
    }
    public List<Board> findPostsByRecommend(int recommend){
        return boardRepository.findByRecommend(recommend);
    }
    public boolean validatePassword(Long id,String inputPassword){
        String password = boardRepository.findPassword(id);
        return password.equals(inputPassword);
    }
    @Transactional
    public void deleteById(Long boardId){
        Board board = boardRepository.findOne(boardId);
        boardRepository.delete(board);
    }
}
