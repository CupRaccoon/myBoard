package cupraccoon.myboard.service;

import cupraccoon.myboard.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CommentNode {
    int depth;
    Long commentId;
    Comment comment;
    List<CommentNode> childComments;
}
