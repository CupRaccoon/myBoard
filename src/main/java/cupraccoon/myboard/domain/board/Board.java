package cupraccoon.myboard.domain.board;

import com.sun.istack.NotNull;
import cupraccoon.myboard.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Board {

        @Id
        @GeneratedValue
        @Column(name = "board_id")
        private Long id;

        @NotNull
        private String title;
        private String content;

        private int recommend;

        private LocalDateTime writeDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User writeUser;

        //commentList

        public Board(){

        }
        public Board(String title, String content){
                this.title = title;
                this.content = content;
                this.recommend = 0;
                this.writeDate = LocalDateTime.now();
        }
        public void addrecommend(){
                this.recommend += 1;
        }
        public void disrecommend(){
                this.recommend += 1;
        }
}


