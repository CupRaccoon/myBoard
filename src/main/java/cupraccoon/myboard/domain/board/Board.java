package cupraccoon.myboard.domain.board;

import com.sun.istack.NotNull;
import cupraccoon.myboard.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

        @Id
        @GeneratedValue
        @Column(name = "board_id")
        private Long id;

        private String dtype;
        @NotNull
        private String title;
        private String content;

        private int recommend;

        private LocalDateTime writeDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User writeUser;

        @ColumnDefault("false")
        private boolean isSignedUser;

        private String unsignedUser;

        private String unsignedPassword;

        //commentList

        public static Board createUnsigned(String dtype, String title,
                                           String content, String user, String password){
                Board board = new Board();
                board.dtype = dtype;
                board.title = title;
                board.content = content;
                board.recommend = 0;
                board.writeDate = LocalDateTime.now();
                board.isSignedUser = false;
                board.unsignedUser = user;
                board.unsignedPassword = password;
                return board;
        }

        public void addrecommend(){
                this.recommend += 1;
        }
        public void disrecommend(){
                this.recommend += 1;
        }
}


