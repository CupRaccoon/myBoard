package cupraccoon.myboard.controller.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class editBoardRequest {
    private boolean isSigned;
    private String userName;
    private String password;
    private String title;
    private String content;
}
