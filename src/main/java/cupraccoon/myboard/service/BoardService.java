package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.board.Board;
import cupraccoon.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if(optionalBoard.isPresent()){
            return optionalBoard.get();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public Page<Board> findAllPosts(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


    public Page<Board> findPostsByCategory(String dtype,Pageable pageable){
        return boardRepository.findByDtype(dtype,pageable);
    }
    public Page<Board> findPostsByRecommend(int recommend, Pageable pageable){
        return boardRepository.findByRecommendGreaterThanEqual(recommend,pageable);
    }
    public boolean validatePassword(Long boardId,String inputPassword){
        Board board = this.findOne(boardId);
        return board.isSamePassword(inputPassword);
    }
    @Transactional
    public void deleteById(Long boardId){
        Board board = this.findOne(boardId);
        boardRepository.delete(board);
    }
    @Transactional
    public void increaseRecommend(Long id){
        Board board = this.findOne(id);
        board.addRecommend(1);
    }

}
