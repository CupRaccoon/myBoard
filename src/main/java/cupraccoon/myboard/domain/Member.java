package cupraccoon.myboard.domain;

import cupraccoon.myboard.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String nickname;

    private String password;

    public static Member createMember(String loginId, String nickname, String password) {
        Member member = new Member();
        member.loginId = loginId;
        member.nickname = nickname;
        member.password = password;
        return member;
    }


}
