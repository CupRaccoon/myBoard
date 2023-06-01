package cupraccoon.myboard.domain;


import cupraccoon.myboard.domain.board.Board;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private LocalDateTime writeDate;
    private int recommend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writeMember;
    private String unsignedMember;
    private String unsignedPassword;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parent_comment;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Comment> child_comments = new ArrayList<>();

    @Builder
    private Comment(String content,int recommend,Board board,Member writeMember, String unsignedMember, String unsignedPassword) {
        this.content = content;
        this.board = board;
        this.recommend = recommend;
        this.writeDate = LocalDateTime.now();
        this.writeMember = writeMember;
        this.unsignedMember = unsignedMember;
        this.unsignedPassword = unsignedPassword;
    }
}


