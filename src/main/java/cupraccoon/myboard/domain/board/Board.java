package cupraccoon.myboard.domain.board;

import com.sun.istack.NotNull;
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

        private int like;

        private LocalDateTime writeDate;

        //UserId
        //commentList
        public Board(){

        }
        public Board(String title, String content){
                this.title = title;
                this.content = content;
                this.like = 0;
                this.writeDate = LocalDateTime.now();
        }
        public void addLike(){
                this.like += 1;
        }
        public void disLike(){
                this.like += 1;
        }
}


