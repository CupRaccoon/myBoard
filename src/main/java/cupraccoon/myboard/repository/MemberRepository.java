package cupraccoon.myboard.repository;

import cupraccoon.myboard.domain.Member;
import cupraccoon.myboard.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByLoginId(String loginId);

}
