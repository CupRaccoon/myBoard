package cupraccoon.myboard.controller.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentRequest {
    private String userName;
    private String password;
    private String content;
}
