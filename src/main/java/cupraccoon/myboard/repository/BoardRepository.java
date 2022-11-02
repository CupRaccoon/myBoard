package cupraccoon.myboard.repository;


import cupraccoon.myboard.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    @PersistenceContext
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
    public List<Board> findAll(){

        return em.createQuery("select i from Board i", Board.class).getResultList();
    }

}

