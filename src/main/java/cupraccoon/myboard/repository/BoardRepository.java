package cupraccoon.myboard.repository;


import cupraccoon.myboard.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public Long save(Board board){
        if(board.getId() == null){
            em.persist(board);
            return board.getId();
        }
        else{
            return 0L;
        }
    }
    public Board findOne(Long id){
        return em.find(Board.class, id);

    }

}

