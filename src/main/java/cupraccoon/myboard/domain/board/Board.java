package cupraccoon.myboard.domain.board;

import com.sun.istack.NotNull;
import cupraccoon.myboard.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;


    @NotBlank
    private String dtype;

    @NotNull
    @Length(min = 1, max = 50)
    private String title;

    @Length(min = 1, max = 5000)
    @NotNull
    private String content;

    @NotNull
    private int recommend;

    @NotBlank
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member writeUser;

    @ColumnDefault("false")
    @Length(min = 1, max = 50)
    @NotNull
    private String unsignedUser;
    @Length(min = 1, max = 50)
    @NotBlank
    private String unsignedPassword;

    //TODO : commentList

    public static Board createUnsigned(String dtype, String title,
                                       String content, String user, String password) {
        Board board = new Board();
        board.dtype = dtype;
        board.title = title;
        board.content = content;
        board.recommend = 0;
        board.writeDate = LocalDateTime.now();
        board.unsignedUser = user;
        board.unsignedPassword = password;
        return board;
    }

    public boolean isSamePassword(String password) {
        return this.unsignedPassword.equals(password);
    }

    public void addRecommend(int addNumber) {
        this.recommend = recommend + addNumber;
    }
}


