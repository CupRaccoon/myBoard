package cupraccoon.myboard.controller.comment;

import cupraccoon.myboard.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    Comment comment;
    int depth;
}
