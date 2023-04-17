package cupraccoon.myboard.domain.board;

import com.sun.istack.NotNull;
import cupraccoon.myboard.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;


    @NotBlank
    private String dtype;

    private String title;

    private String content;

    private int recommend;

    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writeMember;

    private String unsignedMember;

    private String unsignedPassword;

    //TODO : commentList

    @Builder
    private Board(String dtype, String title, String content,
                  int recommend, Member writeMember,
                  String unsignedMember, String unsignedPassword) {
        this.dtype = dtype;
        this.title = title;
        this.content = content;
        this.recommend = recommend;
        this.writeDate = LocalDateTime.now();
        this.writeMember = writeMember;
        this.unsignedMember = unsignedMember;
        this.unsignedPassword = unsignedPassword;
    }

    public void editBoardSigned(String title, String content){
        this.title = title;
        this.content = content;
        this.writeDate = LocalDateTime.now();
    }
    public void editBoardUnsigned(String title, String content, String unsignedMember, String unsignedPassword){
        this.title = title;
        this.content = content;
        this.writeDate = LocalDateTime.now();
        this.unsignedMember = unsignedMember;
        this.unsignedPassword = unsignedPassword;
    }

    public boolean isSamePassword(String password) {
        return this.unsignedPassword.equals(password);
    }

    public void addRecommend(int addNumber) {
        this.recommend = recommend + addNumber;
    }
}


