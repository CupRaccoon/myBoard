package cupraccoon.myboard.repository;


import cupraccoon.myboard.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository_old {

    private final EntityManager em;

    public Long save(Board board) {
        if (board.getId() == null) {
            em.persist(board);
            return board.getId();
        } else {
            return 0L;
        }
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);

    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public List<Board> findByCategory(String dtype) {
        return em.createQuery("select b from Board b where b.dtype= :dtype", Board.class)
                .setParameter("dtype", dtype)
                .getResultList();
    }

    public List<Board> findByRecommend(int recommendNumber) {
        return em.createQuery("select b from Board b where b.recommend >= :recommendNumber",
                Board.class)
                .setParameter("recommendNumber", recommendNumber)
                .getResultList();
    }

    public String getPassword(Long id) {
        return em.find(Board.class, id).getUnsignedPassword();
    }

    public void increaseRecommend(Board board) {
        board.addRecommend(1);
    }

    public void delete(Board board) {
        em.remove(board);
    }

}

