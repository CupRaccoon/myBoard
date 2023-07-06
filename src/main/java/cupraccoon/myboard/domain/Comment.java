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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writeMember;
    private String unsignedMember;
    private String unsignedPassword;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> childComment = new ArrayList<>();

    @Builder
    private Comment(String content,Comment parentComment,Board board,Member writeMember, String unsignedMember, String unsignedPassword) {
        this.content = content;
        this.parentComment = parentComment;
        this.board = board;
        this.writeDate = LocalDateTime.now();
        this.writeMember = writeMember;
        this.unsignedMember = unsignedMember;
        this.unsignedPassword = unsignedPassword;
    }
}


